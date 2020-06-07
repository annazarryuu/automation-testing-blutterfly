package com.id.perpus.transaksi.pinjam;

import com.id.perpus.common.BaseModel;

public class LoanModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nim;
	private String email;
	private String loanNo;
	private String loanDateFrom;
	private String loanDateTo;
	private String memberName;
	private String majors;
	private String majorName;
	private String prodiName;
	
	private String bookCd;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublisher;
	private String bookPublicationYear;
	
	private String loanDate;
	private String returnedDate;
	private String actualReturnedDate;
	private String loanStatus;
	
	private Integer penalty;
	
	public String getBookCd() {
		return bookCd;
	}
	public void setBookCd(String bookCd) {
		this.bookCd = bookCd;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanDateFrom() {
		return loanDateFrom;
	}
	public void setLoanDateFrom(String loanDateFrom) {
		this.loanDateFrom = loanDateFrom;
	}
	public String getLoanDateTo() {
		return loanDateTo;
	}
	public void setLoanDateTo(String loanDateTo) {
		this.loanDateTo = loanDateTo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMajors() {
		return majors;
	}
	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}
	public String getActualReturnedDate() {
		return actualReturnedDate;
	}
	public void setActualReturnedDate(String actualReturnedDate) {
		this.actualReturnedDate = actualReturnedDate;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public Integer getPenalty() {
		return penalty;
	}
	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getProdiName() {
		return prodiName;
	}
	public void setProdiName(String prodiName) {
		this.prodiName = prodiName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookPublicationYear() {
		return bookPublicationYear;
	}
	public void setBookPublicationYear(String bookPublicationYear) {
		this.bookPublicationYear = bookPublicationYear;
	}
	
	
	
	

}
