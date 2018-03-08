package com.example.customer;

import com.example.customer.interceptor.ErrorInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServerApplication.class, args);
	}

}
