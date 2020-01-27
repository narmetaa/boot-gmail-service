package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class GmailServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailServicesApplication.class, args);
		System.out.println("boot application has started");
	}

}
