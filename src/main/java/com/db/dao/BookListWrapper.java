package com.db.dao;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


public class BookListWrapper {
	private List<String> bookisbn;
	private List<String> booktitle;
	private List<Integer> bookcnt;
	/**
	 * @return the bookisbn
	 */
	public List<String> getBookisbn() {
		return bookisbn;
	}
	/**
	 * @param bookisbn the bookisbn to set
	 */
	public void setBookisbn(List<String> bookisbn) {
		this.bookisbn = bookisbn;
	}
	/**
	 * @return the booktitle
	 */
	public List<String> getBooktitle() {
		return booktitle;
	}
	/**
	 * @param booktitle the booktitle to set
	 */
	public void setBooktitle(List<String> booktitle) {
		this.booktitle = booktitle;
	}
	/**
	 * @return the bookcnt
	 */
	public List<Integer> getBookcnt() {
		return bookcnt;
	}
	/**
	 * @param bookcnt the bookcnt to set
	 */
	public void setBookcnt(List<Integer> bookcnt) {
		this.bookcnt = bookcnt;
	}

}
