package com.votacaoalmoco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VotacaoAlmocoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoAlmocoServiceApplication.class, args);
	}

}
