package com.exam.backend_developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendDeveloperApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDeveloperApplication.class, args);
	}

}
