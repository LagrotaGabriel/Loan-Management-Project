package br.com.loanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EntityScan(basePackages = {"br.com.loanapi.models"})
@EnableJpaRepositories("br.com.loanapi.repositories")
@ComponentScan("br.com.loanapi.services")
@ComponentScan("br.com.loanapi.controllers")
@ComponentScan("br.com.loanapi.config")
@ComponentScan("br.com.loanapi.exceptions")
@ComponentScan("br.com.loanapi.validations")
@ComponentScan("br.com.loanapi.utils")
@SpringBootApplication
public class LoanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApiApplication.class, args);
	}

}
