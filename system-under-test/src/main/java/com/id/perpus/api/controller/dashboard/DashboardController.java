package com.id.perpus.api.controller.dashboard;

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

import com.google.gson.Gson;
import com.id.perpus.common.Response;
import com.id.perpus.dashboard.DashboardModel;
import com.id.perpus.dashboard.DashboardService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class DashboardController {
	@Autowired
	private DashboardService service;
	
	@RequestMapping(value="/dash-board")
	public String user() {
		return "app.dashboard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/dash-board")
	public String index() {
		return "API Edu Level Service v0.1";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/dash-board/all", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String search(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "page" , required=false) Integer page) {
		Gson gson = new Gson();
		DashboardModel model = new DashboardModel();
		
		model.setPage(page);
		
		Response responses = service.doSearch(model);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/dash-board/count", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String count(HttpServletRequest request,
							HttpServletResponse response, 
							@RequestParam(value = "page" , required=false) Integer page) {
		Gson gson = new Gson();
		DashboardModel model = new DashboardModel();
		
		model.setPage(page);
		
		Response responses = service.doCountDt(model);
		return gson.toJson(responses);
	}
//	
//	@ResponseBody
//	@RequestMapping(value="/api/edu-level/delete", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//	public String delete(HttpServletRequest request,HttpServletResponse response, 
//			@RequestBody List<String> ids) {
//		Gson gson = new Gson();
//		List<DashboardModel> datas = new ArrayList<DashboardModel>();
//		for(String id : ids){
//			DashboardModel data = new DashboardModel();
//			data.setId(id);
//			datas.add(data);
//		}
//		Response responses = service.doDelete(datas);
//		return gson.toJson(responses);
//	} //end (bhakti)
	/*
	@ResponseBody
	@RequestMapping(value="/api/menu/autocomplate", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String autocomplate(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "term", required=false) String term) {
		Gson gson = new Gson();
		MenuModel model = new MenuModel();
		model.setFind(term);
		
		Response responses = service.doAutoComplate(model);
		return gson.toJson(responses);
	}*/
	
}
