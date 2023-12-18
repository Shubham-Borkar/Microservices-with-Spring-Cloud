package com.product.service;

import java.util.List;

import com.product.pojo.Product;
import com.product.pojo.Review;

public interface ProductService {
	List<Product> getAllProduct();
	Product findProductById(int id);
	List<Review> getReviewById(int id);
}
