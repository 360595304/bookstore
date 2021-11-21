package com.hu.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerUI配置
 * @author NieChangan
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里一定要标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.hu.bookstore.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("网上书店管理系统API文档")
                .description("网上书店管理系统API文档")
                .termsOfServiceUrl("https://github.com/360595304/bookstore")
                .contact(new Contact("suhu","https://github.com/360595304/bookstore","360595304@qq.com"))
                .version("1.0")
                .build();
    }
}

