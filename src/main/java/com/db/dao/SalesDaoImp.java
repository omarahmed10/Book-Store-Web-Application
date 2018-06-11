package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.controller.LoginController;
import com.db.model.BookInfo;
import com.db.model.SaleInfo;
import com.db.model.UserInfo;
import com.db.service.SqlQueryService;

@Repository
public class SalesDaoImp implements SalesDao {
	@Autowired
	SqlQueryService sqlService;

	public List<SaleInfo> totalSalesPreviousMonth() {
		List<SaleInfo> sales = new LinkedList<SaleInfo>();
		ResultSet rs = sqlService.callProcedure(LoginController.con, "Total_Sales_previous_month").rs;
		try {
			while (rs.next()) {
				SaleInfo s = new SaleInfo();
				s.setBookisbn(rs.getString("Book_ISBN"));
				s.setBooktitle(rs.getString("Book_Title"));
				s.setDate(rs.getDate("Sale_Date"));
				s.setUsername(rs.getString("User_Name"));
				s.setQuantity(rs.getInt("Book_Count"));
				sales.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sales;
	}

	public List<UserInfo> top5Customers() {
		List<UserInfo> users = new LinkedList<UserInfo>();
		ResultSet rs = sqlService.callProcedure(LoginController.con, "Top_5_Customers").rs;
		try {
			while (rs.next()) {
				UserInfo u = new UserInfo();
				u.setUsername(rs.getString("User_Name"));
				u.setTotalpaid(rs.getInt("Total_Paid"));
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public List<BookInfo> top10Book() {
		List<BookInfo> books = new LinkedList<BookInfo>();
		ResultSet rs = sqlService.callProcedure(LoginController.con, "Top_10_Books").rs;
		try {
			while (rs.next()) {
				BookInfo b = new BookInfo();
				b.setIsbn(rs.getString("Book_ISBN"));
				b.setTitle(rs.getString("Book_Title"));
				b.setTotalpurchase(rs.getInt("Total_Count"));
				books.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

}
