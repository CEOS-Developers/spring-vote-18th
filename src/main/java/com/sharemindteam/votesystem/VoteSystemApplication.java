package com.sharemindteam.votesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VoteSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteSystemApplication.class, args);
	}

}
