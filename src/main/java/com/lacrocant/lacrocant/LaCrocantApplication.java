package com.lacrocant.lacrocant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LaCrocantApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaCrocantApplication.class, args);
		/* PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String hashedPassword = passwordEncoder.encode("admin");
		System.out.println(hashedPassword); */
	}

}
