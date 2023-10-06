package com.last.lastcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LastcoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastcoinApplication.class, args);
	}

}
