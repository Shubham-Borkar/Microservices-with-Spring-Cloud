package com.orderservice.components;

import java.util.List;

public interface OrderService {
public List<Order> getAllOrders();
public Order placeOrderForIdByQuantityRT(int productId,int quantity);
public Order placeOrderForIdByQuantityFF(int productId,int quantity);
public Order placeOrderForIdByQuantityRTE(int productId, int quantity);

}
