package br.com.loanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"br.com.loanapi.models"})
@EnableJpaRepositories("br.com.loanapi.repositories")
@ComponentScan("br.com.loanapi.services")
@ComponentScan("br.com.loanapi.resources")
@ComponentScan("br.com.loanapi.config")
@ComponentScan("br.com.loanapi.exceptions")
@SpringBootApplication
public class LoanApiApplication {

	//TODO ADICIONAR MORA NO ATRASO DO PAGAMENTO

	public static void main(String[] args) {
		SpringApplication.run(LoanApiApplication.class, args);
	}

}
