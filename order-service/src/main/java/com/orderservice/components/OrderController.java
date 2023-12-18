package com.orderservice.components;

import java.util.HashMap;
import java.util.List;

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
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService oSer;

	@GetMapping
	public List<Order> getOrders() {	
	List<Order> allOrders = oSer.getAllOrders();
	return allOrders;
	}
	
	
	@GetMapping("/rest-template-normal/{product-id}/{quantity}")
	public Order getOrderSummaryRT(@PathVariable("product-id") int productId,@PathVariable int quantity) {
		return oSer.placeOrderForIdByQuantityRT(productId, quantity);
	}
	@GetMapping("/rest-template-with-eureka/{product-id}/{quantity}")
	public Order getOrderSummaryRTE(@PathVariable("product-id") int productId,@PathVariable int quantity) {
		return oSer.placeOrderForIdByQuantityRTE(productId, quantity);
	}
	
	
	@GetMapping("/feign/{product-id}/{quantity}")
	public Order getOrderSummaryF(@PathVariable("product-id") int productId,@PathVariable int quantity) {
		return oSer.placeOrderForIdByQuantityFF(productId, quantity);
	}
	
	//it will give error as their is error in api 	
		@GetMapping("/error-r4j-sample-api")
		public String sampleApi() {
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8899/error-url", String.class);
			return forEntity.getBody();
		}
		
		
}
