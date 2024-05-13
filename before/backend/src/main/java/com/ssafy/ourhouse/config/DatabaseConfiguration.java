package com.ssafy.ourhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.ourhouse.mapper")
public class DatabaseConfiguration {
	
}