package com.revature.AnonymousBookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("test.properties")
public class AnonymousBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnonymousBookStoreApplication.class, args);
	}

}
