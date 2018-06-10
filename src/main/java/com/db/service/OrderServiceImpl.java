package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.OrderDao;
import com.db.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	OrderDao orderDao;

	@Autowired
	public void addOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void confirmOrder(String bookIsbn, String bookTitle) {
		orderDao.confirmOrder(bookIsbn, bookTitle);
	}

	public List<Order> listOrders() {
		return orderDao.listOrders();
	}

	public Order getOrder(String bookIsbn, String bookTitle) {
		return orderDao.getOrder(bookIsbn, bookTitle);
	}

	public int countOrders() {
		return orderDao.countorders();
	}

}
