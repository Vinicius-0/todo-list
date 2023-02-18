package com.api.todo.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(
				DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.todo.controller"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(true)
				.apiInfo(apiInfo());
				
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("To do list")
				.description("Aplicacao para gerenciamento de tasks")
				.version("1.0.0")
				.build();
	}

}
