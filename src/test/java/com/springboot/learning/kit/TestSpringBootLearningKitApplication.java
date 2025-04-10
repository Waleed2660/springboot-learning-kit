package com.springboot.learning.kit;

import org.springframework.boot.SpringApplication;

public class TestSpringBootLearningKitApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootLearningKitApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
