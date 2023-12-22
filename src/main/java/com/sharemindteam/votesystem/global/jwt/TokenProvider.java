package com.sharemindteam.votesystem.global.jwt;

import com.sharemindteam.votesystem.global.content.TokenStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.io.Decoders;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final CustomUserDetailsService customUserDetailsService;
    private static final String AUTHORITIES_KEY = "auth";
    private static final String LOGIN_KEY = "loginId";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final String secret;
    private final long accessTokenValidation;
    private final long refreshTokenValidation;
    private Key key;

    public TokenProvider(
            CustomUserDetailsService customUserDetailsService,
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access_token_validation}") long accessTokenValidation,
            @Value("${jwt.refresh_token_validation}") long refreshTokenValidation) {
        this.secret = secret;
        this.accessTokenValidation = accessTokenValidation;
        this.refreshTokenValidation = refreshTokenValidation;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createRefreshToken(Authentication authentication) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.refreshTokenValidation);
        String authorities =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String loginId = userDetails.getUsername();
        return "refresh " + Jwts.builder()
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512)
                .claim(AUTHORITIES_KEY, authorities)
                .claim(LOGIN_KEY, loginId)
                .compact();
    }

    public String createAccessToken(Authentication authentication) {
        String authorities =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String loginId = userDetails.getUsername();

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.accessTokenValidation);
        return "Bearer " + Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(LOGIN_KEY, loginId)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(String token) {
        String loginId = parseClaims(token).get(LOGIN_KEY).toString();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginId);
        if (loginId == null) {
            throw new SecurityException("Token does not contain email information.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

    public TokenStatus validateToken(String token) {
        System.out.println("token 은 " + token);
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return TokenStatus.VALID;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
            return TokenStatus.INVALID;
        } catch (ExpiredJwtException e) {
            logger.info("만료된 토큰입니다.");
            return TokenStatus.EXPIRED;
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
            return TokenStatus.INVALID;
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
            return TokenStatus.INVALID;
        }
    }

    public String parsingAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String parsingRefreshToken(String refreshToken) {
        if (StringUtils.hasText(refreshToken)) {
            return refreshToken.substring(8);
        }
        return null;
    }

    public String reissueAccessToken(String refreshToken) {
        String jwt = parsingRefreshToken(refreshToken);
        if (StringUtils.hasText(jwt) && (validateToken(jwt) == TokenStatus.VALID)) {
            Authentication authentication = getAuthentication(jwt);
            return createAccessToken(authentication);
        }
        return null;
    }
}
