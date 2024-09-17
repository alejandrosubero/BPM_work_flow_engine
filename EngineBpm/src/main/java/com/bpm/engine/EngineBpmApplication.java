package com.bpm.engine;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EngineBpmApplication {

		private static final Logger logger = LogManager.getLogger(EngineBpmApplication.class);
		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/bpm/swagger-ui.html");
		SpringApplication.run(EngineBpmApplication.class, args);
		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/bpm/swagger-ui.html");
		}
}

