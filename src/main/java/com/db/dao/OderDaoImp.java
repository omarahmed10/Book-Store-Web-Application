package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.controller.LoginController;
import com.db.model.Order;
import com.db.model.SqlResult;
import com.db.service.SqlQueryService;

@Repository
public class OderDaoImp implements OrderDao {

	@Autowired
	SqlQueryService sqlService;

	public void confirmOrder(String bookIsbn, String bookTitle) {
		sqlService.callProcedure(LoginController.con, "confirm_Order", bookIsbn, bookTitle);
	}

	public List<Order> listOrders() {
		SqlResult sq = sqlService.callProcedure(LoginController.con, "List_Orders");
		ResultSet rs = sq.rs;
		List<Order> orders = new LinkedList<Order>();
		try {
			while (rs.next()) {
				Order e = new Order();
				e.setBookisbn(rs.getString("Book_ISBN"));
				e.setBooktitle(rs.getString("Book_Title"));
				e.setQuantity(rs.getInt("Quantity"));
				orders.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	public Order getOrder(String bookIsbn, String bookTitle) {
		return null;
	}

	public int countorders() {
		int x = 0;
		try {
			ResultSet rs = LoginController.con.createStatement().executeQuery("select count(*) As cnt from Orders;");
			if(rs.next()) {
				x = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}

}
