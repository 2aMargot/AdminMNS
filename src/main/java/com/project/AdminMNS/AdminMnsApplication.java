package com.project.AdminMNS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class AdminMnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMnsApplication.class, args);
		System.out.println("Hello World ! ");
	}


}
