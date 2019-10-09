package com.example.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.order.dao")
@SpringBootApplication
public class BadmintoncourtApplication implements CommandLineRunner {

	@Autowired
	private ApplicationStart main;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(BadmintoncourtApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) throws Exception {
		main.start();
	}

}
