package com.product;

//import java.util.Arrays;
//import org.springframework.boot.context.properties.ConfigurationProperties;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.product.config.ConfigClass;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	
	
	
	@Autowired
	private ConfigClass configurations;
	
	/**
	 * @param ctx
	 * @return print all beans when Spring Boot Application run
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			   //Configuration Value using Configuration Class
		   	   System.out.println('\n'+"Configuration using @ConfigurationProperties"+configurations.toString());
		   	   };

//to print been list
			
//			args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//			
//		    };
	}
	


}
