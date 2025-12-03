package org.example.filestoring.documentation;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fileStoringOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Homework File Storing API")
                        .description("REST API для управления пользователями и загрузки домашних заданий")
                        .version("v1"))
                .servers(List.of(new Server().url("/").description("Default server")))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}