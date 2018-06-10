package com.db.service;

import java.util.List;

import com.db.model.Order;

public interface OrderService {

	public void confirmOrder(String bookIsbn, String bookTitle);

	public List<Order> listOrders();

	public Order getOrder(String bookIsbn, String bookTitle);

	public int countOrders();
}
