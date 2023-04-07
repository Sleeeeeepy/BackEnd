package com.vsell.vsell.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("V-SELL REST API")
                .version("v0.0.1")
                .description("버츄얼 아바타 경매 플랫폼 api 명세서");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
