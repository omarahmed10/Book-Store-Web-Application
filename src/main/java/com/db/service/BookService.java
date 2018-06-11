package com.db.service;

import java.util.List;

import com.db.model.BookInfo;
import com.db.model.SqlResult;

public interface BookService {
	public List<BookInfo> listBooks();

	public SqlResult addNewBook(BookInfo b);

	public SqlResult editBook(BookInfo b);

	public List<BookInfo> searchBooks(String type, Object... args);

	public void addToCart(BookInfo b, String userName);

	public List<BookInfo> getCart(String username);

	public void deleteFromCart(String userName, String isbn, String title);
	
	public void buyBooks(List<BookInfo> books,String userName);
}
