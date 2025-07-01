package com.kbsw.pr_1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CGV 클론")
                        .version("0.0.1")
                        .description("<h3>CGV 클론코딩.</h3>"));

        // http://localhost:8080/swagger-ui/index.html
    }
}