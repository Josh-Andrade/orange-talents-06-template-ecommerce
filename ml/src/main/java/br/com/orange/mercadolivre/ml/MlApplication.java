package br.com.orange.mercadolivre.ml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlApplication.class, args);
	}

}
