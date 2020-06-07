package com.id.perpus.book;

import java.util.Date;

import com.id.perpus.common.BaseModel;

public class BookModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookCode;
	private String title;
	private String author;
	private String publisher;
	private String category;
	private String categoryName;
	private String location;
	private String placePublication;
	private String bookCover;
	private String bookSummary;
	private String subject1;
	private String subject2;
	
	private Integer stock;
	private Integer borrowed;
	private Integer lost;
	private Integer avaliable;
	private Integer numberPage;
	private String publishYear;
	private Date publishYearDate;
	
	
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPlacePublication() {
		return placePublication;
	}
	public void setPlacePublication(String placePublication) {
		this.placePublication = placePublication;
	}
	public String getBookCover() {
		return bookCover;
	}
	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	public String getBookSummary() {
		return bookSummary;
	}
	public void setBookSummary(String bookSummary) {
		this.bookSummary = bookSummary;
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public String getSubject2() {
		return subject2;
	}
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
	}	
	public String getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}
	public Date getPublishYearDate() {
		return publishYearDate;
	}
	public void setPublishYearDate(Date publishYearDate) {
		this.publishYearDate = publishYearDate;
	}
	public Integer getAvaliable() {
		return avaliable;
	}
	public void setAvaliable(Integer avaliable) {
		this.avaliable = avaliable;
	}
	public Integer getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(Integer borrowed) {
		this.borrowed = borrowed;
	}
	public Integer getLost() {
		return lost;
	}
	public void setLost(Integer lost) {
		this.lost = lost;
	}
	
	
	
	
}
