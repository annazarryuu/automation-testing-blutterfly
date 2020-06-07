package com.id.perpus.api.controller.auth;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
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
import com.id.perpus.auth.AuthService;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.JwtAuthenticationFilter;
import com.id.perpus.common.Response;
import com.id.perpus.user.UserModel;
import com.id.perpus.user.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index() {
		return "app.katalog";
	}

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView login() {
		Response resp = new Response(false,"","",null);
		return new ModelAndView("app.login","model",resp);
	}
	
	@RequestMapping(value="/forgotPassword",method = RequestMethod.GET)
	public ModelAndView forgotPassword() {
		Response resp = new Response(false,"","",null);
		return new ModelAndView("app.forgotPassword","model",resp);
	}
	
	@RequestMapping(value="/registrasi",method = RequestMethod.GET)
	public ModelAndView regitrasi() {
		Response resp = new Response(false,"","",null);
		return new ModelAndView("app.registrasi","model",resp);
	}
	

	@RequestMapping(value="/admin-dashboard",method = RequestMethod.GET)
	public ModelAndView dashboard() {
		Response resp = new Response(false,"","",null);
		return new ModelAndView("app.home","model",resp);
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		try {
			Response resp = authService.doLogin(username, password);
			if(resp.isStatus()){
				String token = JwtAuthenticationFilter.createJWT(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),username,username,Constanta.MAX_TOKEN_EXPIRED).getToken();
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("token", token);
				session.setMaxInactiveInterval(Constanta.MAX_TOKEN_EXPIRED);
				
				Response userData = userService.doSearchByUsername(username);
				if(userData.getData() != null && userData.getData().size() > 0){
					System.out.print("Berhasil");
					UserModel model = (UserModel)userData.getData().get(0);
					session.setAttribute("userModel",model);
					session.setAttribute("name", model.getName());
					session.setAttribute("role",model.getRoleId());
					session.setAttribute("roleName",model.getRoleName());		
				}
				
				Cookie tokenize = new Cookie("token", token);
				tokenize.setMaxAge(Constanta.MAX_TOKEN_EXPIRED);
				response.addCookie(tokenize);
				return new ModelAndView("redirect:/dash-board");
			}else{
				return new ModelAndView("app.login","model",resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("app.login","model",null);
		}
	}

	@ResponseBody
	@RequestMapping(value="/api/auth/forgot", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String forgot(HttpServletRequest request,
						 HttpServletResponse response, 
						 @RequestParam(value = "insertemail", required=true) String insertemail) {
		Gson gson = new Gson();
		Response responses = authService.doForgot(insertemail);
		return gson.toJson(responses);
	}
		
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,
							  HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
	
}
