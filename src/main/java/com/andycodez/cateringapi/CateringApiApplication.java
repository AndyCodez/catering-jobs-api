package com.andycodez.cateringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CateringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CateringApiApplication.class, args);
	}

}
