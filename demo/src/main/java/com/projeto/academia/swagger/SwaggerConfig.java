package com.projeto.academia.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@EnableSwagger2
@Profile("!test")
public class SwaggerConfig {
    
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.projeto.academia.controller"))
//            .paths(PathSelectors.any())
//            .build()
//            .apiInfo(apiInfo());
//    }
//    
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//
//    private List<springfox.documentation.spi.service.contexts.SecurityContext> securityContexts() {
//        return java.util.Collections.singletonList(
//            springfox.documentation.spi.service.contexts.SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build()
//        );
//    }
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
//        return java.util.Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("API da Academia")
//            .description("Documentação da API da Academia")
//            .version("1.0.0")
//            .build();
//    }
}