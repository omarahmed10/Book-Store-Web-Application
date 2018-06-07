package com.db.model;

import java.sql.Date;

public class BookInfo {
	/*
	 * ISBN VARCHAR(20) NOT NULL, Title VARCHAR(100) NOT NULL, Publish_year DATE,
	 * Price INT, Copies_number INT, Threshold INT NOT NULL, PID INT, Category
	 * Varchar(10),
	 */
	private String isbn;
	private String title;
	private Date pubyear;
	private String publishername;
	private int price;
	private int copiesnums;
	private int threshold;
	private String category;
	private String authors;

	public BookInfo() {
		super();
	}

	public BookInfo(String isbn, String title, Date pubyear, int price, int threshold, int copiesnums,
			String category) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.pubyear = pubyear;
		this.price = price;
		this.threshold = threshold;
		this.copiesnums = copiesnums;
		this.category = category;
	}

	public BookInfo(String isbn, String title, Date pubyear, String publishername, int price, int copiesnums,
			int threshold, String category, String authors) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.pubyear = pubyear;
		this.publishername = publishername;
		this.price = price;
		this.copiesnums = copiesnums;
		this.threshold = threshold;
		this.category = category;
		this.authors = authors;
	}

	/**
	 * @return the copies_nums
	 */
	public int getCopiesnums() {
		return copiesnums;
	}

	/**
	 * @param cn
	 *            the copies_nums to set
	 */
	public void setCopiesnums(int cn) {
		copiesnums = cn;
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
	public Date getPubyear() {
		return pubyear;
	}

	/**
	 * @param py
	 *            the pub_year to set
	 */
	public void setPubyear(Date py) {
		pubyear = py;
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

	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * @param authors
	 *            the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * @return the publishername
	 */
	public String getPublishername() {
		return publishername;
	}

	/**
	 * @param publishername
	 *            the publishername to set
	 */
	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	public String to_String() {
		return "{ " + isbn + ", " + title + ", " + pubyear + ", " + publishername + ", " + authors + "}";
	}
}
