package com.db.dao;

import java.util.List;

import com.db.model.Order;

public interface OrderDao {
	
	public void confirmOrder(String bookIsbn, String bookTitle);
	
	public List<Order> listOrders();
	
	public Order getOrder(String bookIsbn, String bookTitle);

	public int countorders();
}
