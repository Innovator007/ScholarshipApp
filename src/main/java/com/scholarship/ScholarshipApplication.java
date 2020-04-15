package com.scholarship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class ScholarshipApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ScholarshipApplication.class, args);
	}

}
