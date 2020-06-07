package com.id.perpus.api.controller.book;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.id.perpus.book.BookMissingModel;
import com.id.perpus.book.BookModel;
import com.id.perpus.book.BookService;
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Response;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/book")
	public String book() {
		return "app.book";
	}
	
	@RequestMapping(value="/bookForm")
	public ModelAndView bookForm(@RequestParam(value = "bookCd" , required=false) String bookCd) {
		BookModel bookData = new BookModel();
		bookData.setAuthor("");
		bookData.setAvaliable(0);
		bookData.setBookCode("");
		bookData.setBookCover("");
		bookData.setBookSummary("");
		bookData.setCategory("");
		bookData.setNumberPage(0);
		bookData.setLocation("");
		bookData.setPlacePublication("");
		bookData.setStock(0);
		bookData.setSubject1("");
		bookData.setSubject2("");
		bookData.setTitle("");
		
		Map<String, Object> data = new HashMap<String,Object>();
		if (bookCd != null){
			bookData = bookService.getBookById(bookCd);
			data.put("mode", "EDIT");
		}else{
			data.put("mode", "ADD");
		}
		data.put("bookData", bookData);
		
		return new ModelAndView("app.bookForm",data);
	}
	
	@RequestMapping(value="/bookDetail")
	public ModelAndView bookDetail(@RequestParam(value = "bookCd" , required=true) String bookCd) {
		BookModel userData = new BookModel();
		userData = bookService.getBookById(bookCd);
		
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("bookData", userData);
		
		return new ModelAndView("app.bookDetail", data);
	}
	
	@RequestMapping(value="/bookFormMissing")
	public ModelAndView bookFormMissing(@RequestParam(value = "bookCd" , required=true)String bookCd,
										@RequestParam(value = "date" , required=false)String date) {
		BookMissingModel bookData = new BookMissingModel();
		bookData.setBookCd(bookCd);
		bookData.setDate("");
		bookData.setReason("");
		bookData.setNumberOfBook(0);
		
		Map<String, Object> data = new HashMap<String,Object>();
		if (date != null && !date.isEmpty()){
			bookData = bookService.getBookMissingById(bookCd, date);
			data.put("mode", "EDIT");
		}else{
			data.put("mode", "ADD");
		}
		data.put("bookData", bookData);
		
		return  new ModelAndView("app.bookFormMissing", data); 
	}
	
	@ResponseBody
	@RequestMapping(value="/api/book/getComboCategory", method = RequestMethod.GET, produces = "application/json")
	public String getComboStatus(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = bookService.getComboCategory();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/book/search", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String search(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "rowPerPage") Integer rowPerPage,
							  @RequestParam(value = "page") Integer page,
							  @RequestParam(value = "title", required=false) String title,
							  @RequestParam(value = "subject", required=false) String subject,
							  @RequestParam(value = "author", required=false) String author,
							  @RequestParam(value = "publisher", required=false) String publisher,
							  @RequestParam(value = "publishYear", required=false) String publishYear,
							  @RequestParam(value = "category", required=false) String category) {
		Gson gson = new Gson();
		BookModel model = new BookModel();
		
		model.setRowPerPage(rowPerPage);
		model.setLimit(rowPerPage);
		model.setPage(page);
		model.setTitle(title);
		model.setAuthor(author);
		model.setPublisher(publisher);
		model.setPublishYear(publishYear);
		model.setCategory(category);
		model.setSubject1(subject);
		
		Response responses = bookService.doSearch(model);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/book/getBookByCode", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String getBookByCode(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "bookCode", required=true) String bookCode) {
		Gson gson = new Gson();
		Response responses = bookService.getBookByCode(bookCode);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/bookMissing/search", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String search(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "bookCd", required=true) String bookCd) {
		Gson gson = new Gson();
		BookMissingModel model = new BookMissingModel();
		model.setBookCd(bookCd);
		Response responses = bookService.doSearchMissingBook(model);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/book/save", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String saveBook(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "bookCd" , required=false) String bookCd,
									  @RequestParam(value = "title" , required=false) String title,
									  @RequestParam(value = "bookCover" , required=false) String bookCover,
									  @RequestParam(value = "bookSummary" , required=false) String bookSummary,
									  @RequestParam(value = "category" , required=false) String category,
									  @RequestParam(value = "subject1" , required=false) String subject1,
									  @RequestParam(value = "subject2" , required=false) String subject2,
									  @RequestParam(value = "author" , required=false) String author,
									  @RequestParam(value = "publisher" , required=false) String publisher,
									  @RequestParam(value = "publicationYear" , required=false) String publicationYear,
									  @RequestParam(value = "placePublication" , required=false) String placePublication,
									  @RequestParam(value = "numberOfPage" , required=false) String numberOfPage,
									  @RequestParam(value = "location" , required=false) String location,
									  @RequestParam(value = "stock" , required=false) String stock,
									  @RequestParam(value = "mode" , required=false) String mode) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("name").toString();
		Gson gson = new Gson();
		BookModel book = new BookModel();
		
		book.setBookCode(bookCd);
		book.setTitle(title);
		book.setBookCover(bookCover);
		book.setBookSummary(bookSummary);
		book.setCategory(category);
		book.setSubject1(subject1);
		book.setSubject2(subject2);
		book.setAuthor(author);
		book.setPublisher(publisher);
		if (!"".equals(publicationYear)){
			book.setPublishYear(publicationYear);
		}
		book.setPlacePublication(placePublication);
		book.setNumberPage(Common.stringToInteger(numberOfPage));
		book.setLocation(location);
		book.setStock(Common.stringToInteger(stock));
		
		
		book.setCreatedBy(username);
		book.setCreatedDt(Constanta.sdf.format(new Date()));
		book.setUpdatedBy(username);
		book.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		if ("ADD".equals(mode)){
			resp = bookService.doAdd(book);
		}else if ("EDIT".equals(mode)){
			resp = bookService.doEdit(book);
		}
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/bookMissing/save", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String saveBookMissing(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "bookCd" , required=false) String bookCd,
									  @RequestParam(value = "date" , required=false) String date,
									  @RequestParam(value = "reason" , required=false) String reason,
									  @RequestParam(value = "numberOfBook" , required=false) String numberOfBook,
									  @RequestParam(value = "mode" , required=false) String mode) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("name").toString();
		Gson gson = new Gson();
		BookMissingModel book = new BookMissingModel();
		
		book.setBookCd(bookCd);
		book.setDate(date);
		book.setReason(reason);
		book.setNumberOfBook(Common.stringToInteger(numberOfBook));
		
		book.setCreatedBy(username);
		book.setCreatedDt(Constanta.sdf.format(new Date()));
		book.setUpdatedBy(username);
		book.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		if ("ADD".equals(mode)){
			resp = bookService.doAddMissingBook(book);
		}else if ("EDIT".equals(mode)){
			resp = bookService.doEditMissingBook(book);
		}
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/book/delete", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String delete(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "bookCd" , required=false) String bookCode) {
		Gson gson = new Gson();
		BookModel book = new BookModel();
		book.setBookCode(bookCode);
	
		Response resp = null;
		resp = bookService.doDelete(book);
		
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/missingBook/delete", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String delete(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "bookCd" , required=true) String bookCd,
									  @RequestParam(value = "date" , required=true) String date) {
		Gson gson = new Gson();
		BookMissingModel book = new BookMissingModel();
		book.setBookCd(bookCd);
		book.setDate(date);
	
		Response resp = null;
		resp = bookService.doDeleteMissingBook(book);
		
		
		return gson.toJson(resp);
	}
}
