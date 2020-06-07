package com.id.perpus.book;

import com.id.perpus.common.BaseModel;

public class BookMissingModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bookCd;
	private String date;
	private Integer numberOfBook;
	private String reason;
	
	public String getBookCd() {
		return bookCd;
	}
	public void setBookCd(String bookCd) {
		this.bookCd = bookCd;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getNumberOfBook() {
		return numberOfBook;
	}
	public void setNumberOfBook(Integer numberOfBook) {
		this.numberOfBook = numberOfBook;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
