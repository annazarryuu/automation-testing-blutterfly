package com.id.perpus.api.controller.transaksi.pengembalian;

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
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Response;
import com.id.perpus.transaksi.pinjam.LoanModel;
import com.id.perpus.transaksi.pinjam.LoanService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class PengembalianController {
	@Autowired
	private LoanService loanService;
	
	@RequestMapping(value="/kembali")
	public String user() {
		return "app.kembali";
	}
	
	@RequestMapping(value="/kembaliForm")
	public ModelAndView kembaliForm(@RequestParam(value = "loanNo" , required=true) String loanNo) {
	
		Map<String, Object> data = new HashMap<String,Object>();
		LoanModel loanData = loanService.getKembaliDataInfo(loanNo);
		
		data.put("loanData", loanData);
		
		return new ModelAndView("app.kembaliForm",data);
	}
	
	@RequestMapping(value="/kembaliInfo")
	public ModelAndView kembaliInfo(@RequestParam(value = "loanNo" , required=false) String loanNo) {
		Map<String, Object> data = new HashMap<String,Object>();
		LoanModel loanData = loanService.getKembaliDataInfo(loanNo);
		
		data.put("loanData", loanData);
		
		return new ModelAndView("app.kembaliInfo",data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/kembali")
	public String index() {
		return "API Kembali Service v0.1";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/kembali/save", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String daftarUser(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "loanNo" , required=true) String loanNo,
			  						  @RequestParam(value = "actualReturnedDate" , required=true) String actualReturnedDate,
									  @RequestParam(value = "penalty" , required=true) String penalty
										 ) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("name").toString();
		
		Gson gson = new Gson();
		LoanModel pinjam = new LoanModel();
		pinjam.setLoanNo(loanNo);
		pinjam.setActualReturnedDate(actualReturnedDate);
		pinjam.setPenalty(Common.stringToInteger(penalty));
		pinjam.setLoanStatus("BL2");
		pinjam.setCreatedBy(username);
		pinjam.setCreatedDt(Constanta.sdf.format(new Date()));
		pinjam.setUpdatedBy(username);
		pinjam.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = loanService.doKembali(pinjam);
		
		return gson.toJson(resp);
	}
	
}
