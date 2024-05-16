package org.ourhouse.backend.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
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
    public GroupedOpenApi totalApi(){return generateApi(0,"total");}

    @Bean
    public GroupedOpenApi userApi(){return generateApi(1,"user");}

    @Bean
    public GroupedOpenApi freeBoardApi(){return generateApi(2,"free-board");}

    public GroupedOpenApi generateApi(int num, String document){
        return GroupedOpenApi.builder()
                .group(convert(num) + document)
                .pathsToMatch(document.equals("total") ? "/**" : "/" + document + "/**")
                .addOpenApiCustomizer(
                        openApi -> {
                            try{
                                OpenAPI openAPI = objectMapperProvider.yamlMapper()
                                        .readValue(new ClassPathResource("swagger/" + document + ".yaml").getInputStream(), OpenAPI.class);
                                openApi.setInfo(openAPI.getInfo());
                                openApi.setPaths(openAPI.getPaths());
                                openApi.setComponents(openAPI.getComponents());
                            }catch(Exception e){
                                log.info(e.toString());
                            }
                        }
                )
                .build();
    }

    public String convert(int num){
        StringBuilder sb = new StringBuilder();

        if(num < 10){
            sb.append("0");
        }

        return sb.append(num).append(". ").toString();
    }
}
