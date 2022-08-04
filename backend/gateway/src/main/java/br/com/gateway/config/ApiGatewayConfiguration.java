package br.com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter (RouteLocatorBuilder builder) {
        return builder.routes()

                .route(p -> p.path("/api/**")
                        .uri("lb://loan-api"))

                .route(p -> p.path("/installment/**")
                        .uri("lb://installment-service"))

                .build();
    }

}
