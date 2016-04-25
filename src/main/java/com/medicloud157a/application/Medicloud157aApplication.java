package com.medicloud157a.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "controller" })
public class Medicloud157aApplication {

	public static void main(String[] args) {
		SpringApplication.run(Medicloud157aApplication.class, args);
	}
}
