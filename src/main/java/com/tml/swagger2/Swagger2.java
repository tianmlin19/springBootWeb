package com.tml.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by admin on 2019/3/5.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo())
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.tml.controller")) //自动扫描路径
                        .paths(PathSelectors.any())
                        .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                        .title("Spring Boot中使用Swagger2构建Restful APIs")
                        .description("tianmlin19个人测试")
                        .termsOfServiceUrl("https://github.com/tianmlin19/")
                        .version("1.0")
                        .build();
    }


}
