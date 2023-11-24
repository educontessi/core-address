package io.github.educontessi.core.address.adapters.in.rest.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration class
 *
 * @author Eduardo Possamai Contessi
 */
@Configuration
public class SwaggerConfig {

    // http://localhost:8090/api/core-address/swagger-ui.html
    @Bean
    public OpenAPI openAPI(@Value("${application-description}") String appDesciption,
                           @Value("${application-version}") String appVersion) {
        var contact = new Contact();
        contact.setName("Eduardo Possamai Contessi");
        contact.setUrl("https://educontessi.github.io/");
        contact.setEmail("contessi@outlook.com");

        return new OpenAPI()
                .info(new Info().title("Core Address")
                        .version(appVersion)
                        .description(appDesciption)
                        .contact(contact))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation")
                        .url("https://educontessi.github.io/"));
    }

    @Bean
    public GroupedOpenApi groupedOpenApiV1() {
        var packagesToscan = "io.github.educontessi.core.address.adapters.in.rest.v1.controller";
        return GroupedOpenApi.builder()
                .group("core-address-v1")
                .packagesToScan(packagesToscan)
                .build();
    }

}
