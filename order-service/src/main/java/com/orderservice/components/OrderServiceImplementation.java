package com.orderservice.components;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderservice.proxy.ProductServiceProxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired
	private OrderRepository oRepo;
	
	@Autowired
	private Environment environment;
	
	
	@Autowired
	private ProductServiceProxy productServiceProxy;
	
	@Autowired
	private RestTemplate restTemplate;

	
	@Override
	public List<Order> getAllOrders() {
		List<Order> allOrders = oRepo.findAll();
		allOrders.forEach(o -> o.setEnvironment(environment.getProperty("local.server.port")));
		return allOrders;
	}

	@Override
	public Order placeOrderForIdByQuantityRT(int productId,int quantity) {
		/////mentioned overhead is resolved in feign
		
		HashMap<String, Integer> uriVariables=new HashMap<>();
		 uriVariables.put("pid", productId); 
		 RestTemplate restTemplate2=new RestTemplate();
		ResponseEntity<Order> productServiceResponse = restTemplate2.getForEntity("http://localhost:8001/product/getproduct/{pid}", Order.class,uriVariables);
		Order order = productServiceResponse.getBody();
		
		///////
		
		
		order.setQuantity(quantity);
		order.setTotalAmount(quantity*order.getPrice());
		order.setEnvironment(environment.getProperty("local.server.port"));
		System.out.println(order.toString());
		Order savedOrder = oRepo.save(order);
		return savedOrder;
	}
	
	@Override
	public Order placeOrderForIdByQuantityFF(int productId,int quantity) {
		
		Order order = productServiceProxy.getProductById(productId);
		
		order.setQuantity(quantity);
		order.setTotalAmount(quantity*order.getPrice());
		//order.setEnvironment(environment.getProperty("local.server.port"));
		
		System.out.println(order.toString());
		Order savedOrder = oRepo.save(order);
		return savedOrder;
	}




	@Override
	public Order placeOrderForIdByQuantityRTE(int productId, int quantity) {
	/////mentioned overhead is resolved in feign
		
		HashMap<String, Integer> uriVariables=new HashMap<>();
		 uriVariables.put("pid", productId);
		ResponseEntity<Order> productServiceResponse = restTemplate.getForEntity("http://PRODUCT-SERVICE/product/getproduct/{pid}", Order.class,uriVariables);
		Order order = productServiceResponse.getBody();
		
		///////
		
		
		order.setQuantity(quantity);
		order.setTotalAmount(quantity*order.getPrice());
		order.setEnvironment(environment.getProperty("local.server.port"));
		System.out.println(order.toString());
		Order savedOrder = oRepo.save(order);
		return savedOrder;
	}

}
