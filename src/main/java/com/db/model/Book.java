package com.db.model;

import java.sql.Date;

public class Book {
	/*
	 * ISBN VARCHAR(20) NOT NULL, Title VARCHAR(100) NOT NULL, Publish_year DATE,
	 * Price INT, Copies_number INT, Threshold INT NOT NULL, PID INT, Category
	 * Varchar(10),
	 */
	private String isbn;
	private String title;
	private Date pub_year;
	private int price;
	private int copies_nums;
	private int threshold;
	private String category;

	public Book() {
		super();
	}

	public Book(String isbn, String title, Date pub_year, int price, int threshold, int copies_nums, String category) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.pub_year = pub_year;
		this.price = price;
		this.threshold = threshold;
		this.copies_nums = copies_nums;
		this.category = category;
	}

	/**
	 * @return the copies_nums
	 */
	public int getCopies_nums() {
		return copies_nums;
	}

	/**
	 * @param cn
	 *            the copies_nums to set
	 */
	public void setCopies_nums(int cn) {
		copies_nums = cn;
	}

	/**
	 * @return the iSBN
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param i
	 *            the iSBN to set
	 */
	public void setIsbn(String i) {
		isbn = i;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param t
	 *            the title to set
	 */
	public void setTitle(String t) {
		title = t;
	}

	/**
	 * @return the pub_year
	 */
	public Date getPub_year() {
		return pub_year;
	}

	/**
	 * @param py
	 *            the pub_year to set
	 */
	public void setPub_year(Date py) {
		pub_year = py;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param p
	 *            the price to set
	 */
	public void setPrice(int p) {
		price = p;
	}

	/**
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * @param th
	 *            the threshold to set
	 */
	public void setThreshold(int th) {
		threshold = th;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param cat
	 *            the category to set
	 */
	public void setCategory(String cat) {
		this.category = cat;
	}

	@Override
	public String toString() {
		return isbn + " " + title + " " + category;
	}
}
