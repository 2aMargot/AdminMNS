package com.project.adminmns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AdminMnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMnsApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
