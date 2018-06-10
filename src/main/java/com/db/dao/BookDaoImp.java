package com.db.dao;

import java.sql.Connection;
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
		System.out.println("LIST BOOKs___________________" + sq.msg);
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

	public boolean[] checkBooks(BookInfo[] books, int[] bookQuantities) throws SQLException {
		boolean[] checked = new boolean[books.length];

		for (int i = 0; i < books.length; i++) {
			String ISBN = books[i].getIsbn();
			String Title = books[i].getTitle();

			int bookQuantity = bookQuantities[i];

			SqlResult result = sqlService.callProcedure(LoginController.con, "Check_Book_Quantity", ISBN, Title,
					bookQuantity);

			if (result.msg == null) {
				if (result.rs.next()) {
					int valid = result.rs.getInt("valid");
					System.out.println(ISBN + " " + Title + "_______________" + valid);
					if (valid == 1)
						checked[i] = true;
				}
			}
		}

		return checked;
	}

	public boolean[] purchaseBooks(BookInfo[] books, int[] bookQuantities) throws SQLException {
		boolean[] purchased = new boolean[books.length];

		LoginController.con.setAutoCommit(false);
		LoginController.con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

		for (int i = 0; i < books.length; i++) {
			String ISBN = books[i].getIsbn();
			String Title = books[i].getTitle();
			int copiesNum = books[i].getCopiesnums();

			int bookQuantity = bookQuantities[i];

			SqlResult result = sqlService.callProcedure(LoginController.con, "modify_book", ISBN, Title,
					copiesNum - bookQuantity);

			if (result.msg == null)
				purchased[i] = true;
			else {
				System.out.println(result.msg + " : " + " There are not enough book quantities"
						+ " Transaction is being rolled back");
				LoginController.con.rollback();
			}
		}

		LoginController.con.commit();
		LoginController.con.setAutoCommit(true);

		return purchased;
	}

	public void addToCart(BookInfo b, String userName) {
		sqlService.callProcedure(LoginController.con, "Add_To_Cart", b.getIsbn(), b.getTitle(), userName,
				b.getCopiesnums());
	}

	public List<BookInfo> getCart(String username) {
		ResultSet rs = sqlService.callProcedure(LoginController.con, "Get_User_Cart", username).rs;
		List<BookInfo> books = new LinkedList<BookInfo>();
		try {
			while (rs.next()) {
				BookInfo newBook = new BookInfo();
				newBook.setIsbn(rs.getString("Book_ISBN"));
				newBook.setTitle(rs.getString("Book_Title"));
				newBook.setCopiesnums(rs.getInt("Book_Count"));
				int totalPrice = searchBooks("Default", newBook.getIsbn(), newBook.getTitle()).get(0).getPrice()
						* newBook.getCopiesnums();
				newBook.setPrice(totalPrice);
				books.add(newBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
}
