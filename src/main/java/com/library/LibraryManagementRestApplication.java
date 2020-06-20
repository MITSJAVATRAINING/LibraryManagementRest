package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.library"})
public class LibraryManagementRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementRestApplication.class, args);
	}

}
