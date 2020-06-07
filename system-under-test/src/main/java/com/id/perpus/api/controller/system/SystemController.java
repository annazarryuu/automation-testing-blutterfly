package com.id.perpus.api.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.id.perpus.common.Response;
import com.id.perpus.system.SystemService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class SystemController {
	@Autowired
	private SystemService systenService;
	
	@ResponseBody
	@RequestMapping(value="/api/system/getComboPage", method = RequestMethod.GET, produces = "application/json")
	public String getComboStatus(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = systenService.getComboPage();
		return gson.toJson(responses);
	}
}
