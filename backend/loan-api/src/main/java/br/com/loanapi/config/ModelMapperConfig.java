package br.com.loanapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Class that makes the ModelMapper configuration
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 04/07/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/tree/master/backend/loan-api/src/main/java/br/com/loanapi/config/ModelMapperConfig.java */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
