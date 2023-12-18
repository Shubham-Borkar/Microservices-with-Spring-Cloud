package com.orderservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderservice.components.Order;
//without eureka
//@FeignClient(name="product-service",url="localhost:8001")

@FeignClient(name="product-service")
public interface ProductServiceProxy {
	//copied from product service return type in 
	//product-service=product
	//product will be mapped to Order
	@GetMapping("/product/getproduct/{id}")
	public Order getProductById(@PathVariable int id);
}
