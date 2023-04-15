package com.example.myseries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MySeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySeriesApplication.class, args);
	}

}
