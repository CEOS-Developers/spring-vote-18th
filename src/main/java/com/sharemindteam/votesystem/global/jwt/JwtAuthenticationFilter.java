package com.sharemindteam.votesystem.global.jwt;

import com.sharemindteam.votesystem.global.content.TokenStatus;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private static final Set<String> PERMIT_ALL_PATHS = Set.of(
            "/auth/signUp",
            "/auth/signIn",
            "/auth",
            "/user/email",
            "/user/loginId",
            "/demoday",
            "/partLeader",
            "/swagger-ui/**",
            "/api-docs/**"
    );

    private TokenProvider tokenProvider;

    public JwtAuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    private boolean isPermitAllPath(String requestURI) {
        return PERMIT_ALL_PATHS.stream().anyMatch(path -> pathMatcher.match(path, requestURI));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = tokenProvider.parsingAccessToken((HttpServletRequest) request);
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        TokenStatus tokenStatus = tokenProvider.validateToken(jwt);
        if (isPermitAllPath(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (StringUtils.hasText(jwt) && (tokenStatus == TokenStatus.VALID)) {
            Authentication auth = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else if (tokenStatus == TokenStatus.EXPIRED) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("accessToken expired");
            return;
        } else {
            logger.debug("Invalid Token, uri: {}", requestURI);
        }
        filterChain.doFilter(request, response);
    }
}
