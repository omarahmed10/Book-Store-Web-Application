package com.db.model;

import java.sql.Date;

public class SaleInfo {
	private String bookisbn;
	private String booktitle;
	private Date date;
	private String username;
	private int quantity;
	/**
	 * @return the bookisbn
	 */
	public String getBookisbn() {
		return bookisbn;
	}
	/**
	 * @param bookisbn the bookisbn to set
	 */
	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}
	/**
	 * @return the booktitle
	 */
	public String getBooktitle() {
		return booktitle;
	}
	/**
	 * @param booktitle the booktitle to set
	 */
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
