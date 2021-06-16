package com.Toucomex.Importation_Toucomex;
//
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ImportationToucomexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportationToucomexApplication.class, args);
	}
}
