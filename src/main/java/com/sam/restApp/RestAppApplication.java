package com.sam.restApp;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestAppApplication {

	private static final Logger log = LogManager.getLogger(RestAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

}
