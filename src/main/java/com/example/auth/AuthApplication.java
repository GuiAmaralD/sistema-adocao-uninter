package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);

		byte[] encoded = Base64.getEncoder().encode("Hello".getBytes());
		System.out.println(new String(encoded));   // Outputs "SGVsbG8="

		byte[] decoded = Base64.getDecoder().decode(encoded);
		System.out.println(new String(decoded));
	}

}
