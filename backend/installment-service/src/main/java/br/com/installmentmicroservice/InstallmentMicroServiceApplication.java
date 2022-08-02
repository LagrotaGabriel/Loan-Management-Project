package br.com.installmentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@EntityScan(basePackages = {"br.com.installmentmicroservice.models"})
@ComponentScan("br.com.installmentmicroservice.services")
@ComponentScan("br.com.installmentmicroservice.resources")
@ComponentScan("br.com.installmentmicroservice.config")
@ComponentScan("br.com.installmentmicroservice.utils")
@SpringBootApplication
public class InstallmentMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstallmentMicroServiceApplication.class, args);
	}

}
