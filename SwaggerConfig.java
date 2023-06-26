package com.example.LaptopExercise.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://localhost:8080/swagger-ui/index.html#/
 */
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
        @Bean
        public OpenAPI api(){
            return new OpenAPI().info(new Info()
                    .title("Swagger laptop API")
                    .version("1.0")
                    .description("Spring doc"));
        }

}
