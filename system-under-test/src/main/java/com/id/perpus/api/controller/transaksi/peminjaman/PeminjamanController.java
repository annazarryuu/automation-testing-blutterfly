package com.id.perpus.api.controller.transaksi.peminjaman;

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
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Response;
import com.id.perpus.transaksi.pinjam.LoanModel;
import com.id.perpus.transaksi.pinjam.LoanService;
import com.id.perpus.user.UserModel;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class PeminjamanController {
	
	@Autowired
	private LoanService loanService;
	
	
	@RequestMapping(value="/pinjam")
	public String loan() {
		return "app.pinjam";
	}
	
	@RequestMapping(value="/loanHistory")
	public String loanHistory() {
		return "app.loanHistory";
	}
	
	@RequestMapping(value="/loanInfo")
	public ModelAndView kembaliInfo(@RequestParam(value = "loanNo" , required=false) String loanNo) {
		Map<String, Object> data = new HashMap<String,Object>();
		LoanModel loanData = loanService.getKembaliDataInfo(loanNo);
		
		data.put("loanData", loanData);
		
		return new ModelAndView("app.loanInfo",data);
	}
	
	@RequestMapping(value="/pinjamForm")
	public ModelAndView bookForm(@RequestParam(value = "loanNo" , required=false) String loanNo) {
		LoanModel loanData = new LoanModel();
		loanData.setNim("");
		loanData.setBookCd("");
		Map<String, Object> data = new HashMap<String,Object>();
		if (loanNo != null){
			loanData = loanService.getLoanDataById(loanNo);
			data.put("mode", "EDIT");
		}else{
			data.put("mode", "ADD");
		}
		data.put("loanData", loanData);
		
		return new ModelAndView("app.pinjamForm",data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/pinjam")
	public String index() {
		return "API Pinjam Service v0.1";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/pinjam/search", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String search(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "rowPerPage") Integer rowPerPage,
							  @RequestParam(value = "page") Integer page,
							  @RequestParam(value = "memberName", required=false) String memberName,
							  @RequestParam(value = "majors", required=false) String majors,
							  @RequestParam(value = "loanDateFrom", required=false) String loanDateFrom,
							  @RequestParam(value = "loanDateTo", required=false) String loanDateTo,
							  @RequestParam(value = "status", required=false) String status,
							  @RequestParam(value = "bookTitle", required=false) String bookTitle,
							  @RequestParam(value = "bookAuthor", required=false) String bookAuthor) {
		HttpSession session = request.getSession();
		UserModel userData = (UserModel) session.getAttribute("userModel");
		
		Gson gson = new Gson();
		LoanModel model = new LoanModel();
		
		model.setRowPerPage(rowPerPage);
		model.setLimit(rowPerPage);
		model.setPage(page);
		model.setMemberName(memberName);
		model.setMajors(majors);
		model.setLoanDateFrom(loanDateFrom);
		model.setLoanDateTo(loanDateTo);
		model.setLoanStatus(status);
		model.setBookTitle(bookTitle);
		model.setBookAuthor(bookAuthor);
		if(Constanta.ROLE_MEMBER.equals(userData.getRoleId())){
			model.setNim(userData.getNim());
		}
		Response responses = loanService.doSearch(model);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/pinjam/save", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String daftarUser(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "loanNo" , required=true) String loanNo,
			  						  @RequestParam(value = "nim" , required=true) String nim,
									  @RequestParam(value = "bookCd" , required=true) String bookCd,
									  @RequestParam(value = "loanDate" , required=true) String loadDate,
									  @RequestParam(value = "returnDate" , required=true) String returnDate,
									  @RequestParam(value = "mode" , required=true) String mode
										 ) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("name").toString();
		
		Gson gson = new Gson();
		LoanModel pinjam = new LoanModel();
		pinjam.setLoanNo(loanNo);
		pinjam.setBookCd(bookCd);
		pinjam.setNim(nim);
		pinjam.setLoanDate(loadDate);
		pinjam.setReturnedDate(returnDate);
		
		pinjam.setCreatedBy(username);
		pinjam.setCreatedDt(Constanta.sdf.format(new Date()));
		pinjam.setUpdatedBy(username);
		pinjam.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		if ("ADD".equals(mode)){
			resp = loanService.doAdd(pinjam);
		}else if ("EDIT".equals(mode)){
			resp = loanService.doEdit(pinjam);
		}
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/pinjam/delete", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String delete(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "loanNo" , required=false) String loanNo) {
		Gson gson = new Gson();
		LoanModel loan = new LoanModel();
		loan.setLoanNo(loanNo);
	
		Response resp = null;
		resp = loanService.doDelete(loan);
		
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/pinjam/getComboStatus", method = RequestMethod.GET, produces = "application/json")
	public String getComboStatus(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = loanService.getComboStatus();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/pinjam/getComboNIM", method = RequestMethod.GET, produces = "application/json")
	public String getComboNIM(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = loanService.getComboNIM();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/pinjam/getComboBook", method = RequestMethod.GET, produces = "application/json")
	public String getComboBook(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = loanService.getComboBook();
		return gson.toJson(responses);
	}
	
}
