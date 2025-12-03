package org.example.fileanalysis.infrastructure.web.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fileAnalysisOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Homework Analysis API")
                        .description("REST API для запуска анализа домашнего задания и получения отчетов")
                        .version("v1"))
                .servers(List.of(new Server().url("/").description("Default server")))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}