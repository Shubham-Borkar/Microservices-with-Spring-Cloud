package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.pojo.Product;
import com.product.pojo.Review;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService pService;
	@Autowired
	private ProductRepository pRepo;

	@GetMapping
	public List<Product> getAllProducts() {
		return pService.getAllProduct();
		
	}
	@GetMapping("/getproduct/{id}")
	public Product getProductById(@PathVariable int id) {
		  Product findProductById = pService.findProductById(id);
		  return findProductById;
	}
	@GetMapping("/review/{id}")
	public List<Review> getAllReview(@PathVariable int id) {
		  return pService.getReviewById(id);
	}
	@GetMapping("/name/{name}")
	public Product getAllReview(@PathVariable String name) {
		  return pRepo.findProductByName(name);
	}
}
