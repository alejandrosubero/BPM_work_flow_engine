package com.bpm.engine;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class EngineBpmApplication {

		protected static final Log logger = LogFactory.getLog(EngineBpmApplication.class);
		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/bpm/swagger-ui.html");
		SpringApplication.run(EngineBpmApplication.class, args);
		logger.info("the document  Swagger is in link: ==>  http://localhost:1111/bpm/swagger-ui.html");
		}
}

