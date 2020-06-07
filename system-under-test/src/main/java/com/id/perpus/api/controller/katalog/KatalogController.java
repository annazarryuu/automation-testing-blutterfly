package com.id.perpus.api.controller.katalog;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.id.perpus.book.BookModel;
import com.id.perpus.book.BookService;
import com.id.perpus.common.Response;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class KatalogController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/katalog")
	public String katalog() {
		return "app.katalog";
	}
	
	@RequestMapping(value="/katalogDetail")
	public ModelAndView bookDetail(@RequestParam(value = "bookCd" , required=true) String bookCd) {
		BookModel userData = new BookModel();
		userData = bookService.getBookById(bookCd);
		
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("bookData", userData);
		
		return new ModelAndView("app.katalogDetail", data);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/api/katalog")
	public String index() {
		return "API Katalog Service v0.1";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/katalog/search", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String search(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "searchText", required=false) String searchText,
							  @RequestParam(value = "page") Integer page) {
		Gson gson = new Gson();
		BookModel model = new BookModel();
		
//		model.setSearchText(searchText);
		model.setPage(page);
		
		Response responses = bookService.doSearch(model);
		return gson.toJson(responses);
	}
	@ResponseBody
	@RequestMapping(value="/api/katalog/getcombo", method = RequestMethod.GET, produces = "application/json")
	public String autocomplate(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = bookService.getCombo();
		return gson.toJson(responses);
	}
}
