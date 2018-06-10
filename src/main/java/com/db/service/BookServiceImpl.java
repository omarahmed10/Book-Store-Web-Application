package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.BookDao;
import com.db.model.BookInfo;
import com.db.model.SqlResult;

@Service
public class BookServiceImpl implements BookService {

	BookDao bookDao;

	@Autowired
	public void addBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<BookInfo> listBooks() {
		return bookDao.listBooks();
	}

	public SqlResult addNewBook(BookInfo b) {
		return bookDao.addNewBook(b);
	}

	public SqlResult editBook(BookInfo b) {
		return bookDao.editBook(b);
	}

	public List<BookInfo> searchBooks(String type, Object... args){
		return bookDao.searchBooks(type, args);
	}
	
	public void addToCart(BookInfo b, String userName) {
		bookDao.addToCart(b,userName);
	}

	public List<BookInfo> getCart(String username) {
		return bookDao.getCart(username);
	}
}
