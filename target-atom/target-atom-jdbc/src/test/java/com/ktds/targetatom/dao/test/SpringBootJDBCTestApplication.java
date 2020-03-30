package com.ktds.targetatom.dao.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.ktds.targetatom")
@ImportResource("classpath:application-context.xml")
@PropertySource(value = {"classpath:application.properties", "classpath:sql.properties"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringBootJDBCTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJDBCTestApplication.class, args);
	}
}
