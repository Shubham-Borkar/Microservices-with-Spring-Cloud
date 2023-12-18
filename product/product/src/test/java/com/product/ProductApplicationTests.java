package com.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.service.ProductService;

@SpringBootTest
class ProductApplicationTests {
	
	@Autowired
	private ProductService pService; 

	@Test
	void contextLoads() {
		assertEquals(pService.findProductById(1).getName(), "Watch");
		
	}

}
