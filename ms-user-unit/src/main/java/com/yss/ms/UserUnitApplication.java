package com.yss.ms;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableSwagger2Doc
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class UserUnitApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserUnitApplication.class, args);
	}
}
