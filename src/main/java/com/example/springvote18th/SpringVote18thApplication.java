package com.example.springvote18th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringVote18thApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVote18thApplication.class, args);
    }

}
