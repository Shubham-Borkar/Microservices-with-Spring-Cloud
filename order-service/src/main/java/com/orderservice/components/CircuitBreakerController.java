package com.orderservice.components;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CircuitBreakerController {
	
	@Autowired
	private OrderRepository oRepo;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/resilience4j/{product-id}/{quantity}")
	//retry it will call fallback method after max attempts specified
	//@Retry(name = "retry-product-service",fallbackMethod ="productServiceBackup" )
	//cb will call fallback method but if more no of failing is continued it will directly call fallback 
	//without calling this to avoid load, and goes in open state 
	//then it limit no of call to this, if it detect success 
	//it goes to half-open and after desired successful call it again goes to close state
	@CircuitBreaker(name="cb-product-service",fallbackMethod = "productServiceBackup")
	//limit no of calls to this specified on property file 
	@RateLimiter(name = "rl-product-service")
	public Order getOrderSummaryRTER4J(@PathVariable("product-id") int productId,@PathVariable int quantity) {
		   log.info("Retrying Call with Product Service");
			
			HashMap<String, Integer> uriVariables=new HashMap<>();
			 uriVariables.put("pid", productId);
			 RestTemplate restTemplate3=new RestTemplate();
			ResponseEntity<Order> productServiceResponse = restTemplate3.getForEntity("http://error-url", Order.class,uriVariables);
			Order order = productServiceResponse.getBody();
			
			order.setQuantity(quantity);
			order.setTotalAmount(quantity*order.getPrice());
			order.setEnvironment(environment.getProperty("local.server.port"));
			System.out.println(order.toString());
			Order savedOrder = oRepo.save(order);
			return savedOrder;	
			}
	//Backup if product service goes down
	public Order productServiceBackup(Throwable throwable) {
		return new Order(0, 0, 0, 0, "Product-R4J", 0,"R4J-Product-Backup");
	}


}
