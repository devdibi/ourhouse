package org.ourhouse.backend.config.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Slf4j
@AllArgsConstructor
public class OpenAPIConfig {

    private final ObjectMapperProvider objectMapperProvider;

    @Bean
    public GroupedOpenApi customOpenAPI(){
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/users/**")
                .addOpenApiCustomizer(openApiCustomizer())
                .build();
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer(){
        return openApi -> {
            try{
                OpenAPI openAPI = objectMapperProvider.yamlMapper()
                        .readValue(new ClassPathResource("swagger/user.yaml").getInputStream(), OpenAPI.class);
                openApi.setInfo(openAPI.getInfo());
                openApi.setPaths(openAPI.getPaths());
                openApi.setComponents(openAPI.getComponents());
            }catch(Exception e){
                log.info(e.toString());
            }
        };
    }

}
