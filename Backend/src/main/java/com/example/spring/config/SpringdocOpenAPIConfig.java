package com.example.spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringdocOpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Service A API")
                        .version("1.0")
                        .description("API documentation for Service A")
                        .contact(new Contact()
                                .name("LGB")
                                .email("bb@gmail.com")
                                .url("https://www.facebook.com/LGBPTIT/"))
                        .license(new License()
                                .name("License")
                                .url("https://example.com/license")));
    }

    @Bean
    public GroupedOpenApi customApi(SwaggerUiConfigParameters swaggerUiConfigParameters) {
        return GroupedOpenApi.builder()
                .group("Group AA")
                .packagesToScan("com.example.spring.controller") // Đổi thành package của service bạn
                .build();
    }
}