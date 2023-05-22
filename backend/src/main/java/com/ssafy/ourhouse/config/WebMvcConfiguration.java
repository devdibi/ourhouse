package com.ssafy.ourhouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
//@MapperScan(basePackages = { "com.ssafy.ourhouse.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//
//		registry.addMapping("/**").allowedOrigins("*")
//				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
//						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
//						HttpMethod.PATCH.name())
//				.allowCredentials(false).maxAge(1800);
//	}

//	Swagger UI 실행시 404처리
//	Swagger2 일경우
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/swagger-ui.html**")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
