package com.sapient.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.sapient.tms") 
public class TmsEmployeeSafetyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsEmployeeSafetyApplication.class, args);
	}

}
