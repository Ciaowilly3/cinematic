package com.cinematic.cinematic.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinematic api")
                        .description("api for cinematic site")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("massi")
                                .email("muscomassi@gmail.com")));
    }
}