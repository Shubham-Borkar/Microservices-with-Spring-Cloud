package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.product.pojo.Product;
import com.product.pojo.Review;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private Environment environment;
	
	public List<Product> getAllProduct() {
		List<Product> allProducts = productRepo.findAll();
		allProducts.forEach(p -> p.setEnvironment(environment.getProperty("local.server.port")));
		return allProducts;
	}

	@Override
	public Product findProductById(int id) {
		Product product = productRepo.findById(id).orElse(null);
		if(product==null) {
			return null;
		}
		else {
		product.setEnvironment(environment.getProperty("local.server.port"));
		return product;
		}
	}

	@Override
	public List<Review> getReviewById(int id) {
		Product product = productRepo.findById(id).orElse(null);
		List<Review> rlist=null;
		if(product!=null) {
			rlist=product.getReviews();
			}
		
		return rlist;
	}

}
