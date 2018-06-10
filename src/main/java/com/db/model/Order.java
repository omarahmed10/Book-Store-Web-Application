package com.db.model;

public class Order {
	private int quantity;
	private String bookisbn;
	private String booktitle;
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
