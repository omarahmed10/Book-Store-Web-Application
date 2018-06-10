package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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
		SqlResult sq = sqlService.callProcedure(LoginController.con, "List_Books");
		System.out.println("LIST BOOKs___________________"+sq.msg);
		return reformateBooks(sq.rs);
	}

	public SqlResult addNewBook(BookInfo b) {
		SqlResult results = sqlService.callProcedure(LoginController.con, "add_new_book", b.getIsbn(), b.getTitle(),
				b.getPubyear(), b.getPrice(), b.getThreshold(), b.getCopiesnums(), b.getPublishername(), b.getAuthors(),
				b.getCategory());
		return results;
	}

	public SqlResult editBook(BookInfo b) {
		SqlResult results = sqlService.callProcedure(LoginController.con, "modify_book", b.getIsbn(), b.getTitle(),
				b.getPubyear(), b.getPrice(), b.getThreshold(), b.getCopiesnums(), b.getPublishername(), b.getAuthors(),
				b.getCategory());
		return results;
	}

	public List<BookInfo> searchBooks(String type, Object... args) {
		SqlResult sq = sqlService.callProcedure(LoginController.con, type + "_Book_Search", args);
		return reformateBooks(sq.rs);
	}

	private List<BookInfo> reformateBooks(ResultSet rs) {
		Map<BookInfo, StringBuilder> bookMap = new HashMap<BookInfo, StringBuilder>();
		try {
			while (rs.next()) {
				BookInfo newBook = new BookInfo();
				newBook.setIsbn(rs.getString("ISBN"));
				newBook.setTitle(rs.getString("Title"));
				if (bookMap.containsKey(newBook)) {
					bookMap.get(newBook).append(", " + rs.getString("Author"));
				} else {
					newBook.setPublishername(rs.getString("PName"));
					newBook.setPubyear(rs.getDate("Publish_year"));
					newBook.setThreshold(rs.getInt("Threshold"));
					newBook.setPrice(rs.getInt("Price"));
					newBook.setCategory(rs.getString("Category"));
					newBook.setCopiesnums(rs.getInt("Copies_number"));
					bookMap.put(newBook, new StringBuilder(rs.getString("Author")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final List<BookInfo> books = new LinkedList<BookInfo>();
		bookMap.forEach(new BiConsumer<BookInfo, StringBuilder>() {
			public void accept(BookInfo k, StringBuilder v) {
				k.setAuthors(v.toString());
				books.add(k);
			}
		});
		return books;
	}
}
