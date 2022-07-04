package br.com.loanapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

/** Class that makes the Swagger configuration
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 04/07/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/tree/master/backend/loan-api/src/main/java/br/com/loanapi/config/SwaggerConfig.java */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.frases"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        return new ApiInfo(
                "Frases",
                "API Rest para recebimento e envio de frases",
                "1.0.0",
                "Terms of Service",
                new Contact("Gabriel Lagrota",
                        "https://github.com/LagrotaGabriel/API-Frases",
                        "gabriellagrota23@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licensen.html",
                new ArrayList<VendorExtension>()
        );
    }

}
