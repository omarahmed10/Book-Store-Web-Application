package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.controller.LoginController;
import com.db.model.BookInfo;
import com.db.model.SqlResult;
import com.db.service.SqlQueryService;

@Repository
public class BookDaoImp implements BookDao {

	@Autowired
	SqlQueryService sqlService;

	public List<BookInfo> listBooks() {
		ResultSet rs;
		List<BookInfo> books = new ArrayList<BookInfo>();
		try {
			rs = LoginController.con.prepareStatement("select * from Book;").executeQuery();
			while (rs.next()) {
				books.add(new BookInfo(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	public SqlResult addNewBook(BookInfo b) {
		SqlResult results = sqlService.callProcedure(LoginController.con, "add_new_book", b.getIsbn(), b.getTitle(),
				b.getPubyear(), b.getPrice(), b.getThreshold(), b.getCopiesnums(), b.getPublishername(), b.getAuthors(),
				b.getCategory());
		return results;
	}

	public void editBook(BookInfo b) {
	}

	public List<BookInfo> searchBooks(String type, List<String> args) {
		ResultSet rs = sqlService.callProcedure(LoginController.con, type + "_Book_Search", args).rs;
		List<BookInfo> books = new ArrayList<BookInfo>();
		try {
			rs = LoginController.con.prepareStatement("select * from Book;").executeQuery();
			while (rs.next()) {
				books.add(new BookInfo(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

}
