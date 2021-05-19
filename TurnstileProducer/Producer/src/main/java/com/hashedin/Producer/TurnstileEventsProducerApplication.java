package com.hashedin.Producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TurnstileEventsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnstileEventsProducerApplication.class, args);
	}

}
