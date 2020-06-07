package com.id.perpus.api.controller.user;

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
import com.id.perpus.user.UserModel;
import com.id.perpus.user.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userVerification")
	public String userVerification() {
		return "app.userVerification";
	}
	
	@RequestMapping(value="/user")
	public String user() {
		return "app.user";
	}
	
	@RequestMapping(value="/userForm")
	public ModelAndView userForm(@RequestParam(value = "email" , required=false) String email) {
		System.out.println(email);
		UserModel userData = new UserModel();
		userData.setName("");
		userData.setEmail("");
		userData.setRoleId("");
		userData.setNim("");
		userData.setJurusan("");
		userData.setProgramStudi("");
		userData.setNip("");
		userData.setDivision("");
		Map<String, Object> data = new HashMap<String,Object>();
		if (email != null){
			userData = userService.doGetModelUserById(email);
			data.put("mode", "EDIT");
		}else{
			data.put("mode", "ADD");
		}
		data.put("userData", userData);
		
		return new ModelAndView("app.userForm",data);
	}
	
	@RequestMapping(value="/profile")
	public String profile() {
		return "app.profile";
	}
	
	@RequestMapping(value="/changeProfile")
	public String changeProfile() {
		return "app.changeProfile";
	}
	
	@RequestMapping(value="/uploadProfilePicture")
	public String uplodaProfilePicture() {
		return "app.uploadProfilePicture";
	}
	
	@RequestMapping(value="/changePassword")
	public String changePassword() {
		return "app.changePassword";
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/user")
	public String index() {
		return "API User Service v0.1";
	}
	
	// api 
	@ResponseBody
	@RequestMapping(value="/api/user/registrasi", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String daftarUser(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "name" , required=false) String name,
									  @RequestParam(value = "nim" , required=false) String nim,
									  @RequestParam(value = "email" , required=false) String email,
									  @RequestParam(value = "prodi" , required=false) String prodi,
									  @RequestParam(value = "jurusan" , required=false) String jurusan) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setName(name);
		user.setNim(nim);
		user.setEmail(email);
		user.setProgramStudi(prodi);
		user.setJurusan(jurusan);
		user.setCreatedBy(name);
		user.setCreatedDt(Constanta.sdf.format(new Date()));
		user.setUpdatedBy(name);
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doRegistrasi(user);
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/save", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String saveUser(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "name" , required=false) String name,
									  @RequestParam(value = "email" , required=false) String email,
									  @RequestParam(value = "role" , required=false) String role,
									  @RequestParam(value = "nim" , required=false) String nim,
									  @RequestParam(value = "majors" , required=false) String majors,
									  @RequestParam(value = "prodi" , required=false) String prodi,
									  @RequestParam(value = "nip" , required=false) String nip,
									  @RequestParam(value = "division" , required=false) String division,
									  @RequestParam(value = "mode" , required=false) String mode) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("name").toString();
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setName(name);
		user.setEmail(email);
		user.setRoleId(role);
		user.setNim(nim);
		user.setProgramStudi(prodi);
		user.setJurusan(majors);
		user.setNip(nip);
		user.setDivision(division);
		
		user.setCreatedBy(username);
		user.setCreatedDt(Constanta.sdf.format(new Date()));
		user.setUpdatedBy(username);
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		if ("ADD".equals(mode)){
			resp = userService.doAdd(user);
		}else if ("EDIT".equals(mode)){
			resp = userService.doEdit(user);
		}
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/updateProfile", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String updateProfile(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "name" , required=false) String name,
									  @RequestParam(value = "email" , required=false) String email,
									  @RequestParam(value = "role" , required=false) String role,
									  @RequestParam(value = "nim" , required=false) String nim,
									  @RequestParam(value = "majors" , required=false) String majors,
									  @RequestParam(value = "prodi" , required=false) String prodi,
									  @RequestParam(value = "nip" , required=false) String nip,
									  @RequestParam(value = "division" , required=false) String division,
									  @RequestParam(value = "mode" , required=false) String mode) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("name").toString();
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setName(name);
		user.setEmail(email);
		user.setRoleId(role);
		user.setNim(nim);
		user.setProgramStudi(prodi);
		user.setJurusan(majors);
		user.setNip(nip);
		user.setDivision(division);
		
		user.setCreatedBy(username);
		user.setCreatedDt(Constanta.sdf.format(new Date()));
		user.setUpdatedBy(username);
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doEdit(user);
		// reset session
		Response userData = userService.doSearchByUsername(email);
		if(userData.getData() != null && userData.getData().size() > 0){
			UserModel model = (UserModel)userData.getData().get(0);
			session.setAttribute("userModel",model);
		}
		return gson.toJson(resp);
	}
	@ResponseBody
	@RequestMapping(value="/api/user/delete", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String delete(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "email" , required=false) String email) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		user.setEmail(email);
	
		Response resp = null;
		resp = userService.doDelete(user);
		
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/changeProfilePicture", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String updateProfilePicture(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "email" ) String email,
									  @RequestParam(value = "profile_picture") String profilePicture) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setEmail(email);
		user.setProfilePicture(profilePicture);
		user.setUpdatedBy(email);
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doUpdateProfilePicture(user);
		// reset session
		HttpSession session = request.getSession();
		Response userData = userService.doSearchByUsername(email);
		if(userData.getData() != null && userData.getData().size() > 0){
			UserModel model = (UserModel)userData.getData().get(0);
			session.setAttribute("userModel",model);
		}
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/changepassword", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String updatePassword(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "email" ) String email,
									  @RequestParam(value = "password") String password) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setEmail(email);
		user.setPassword(password);
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doUpdatePassword(user);
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/updateStatus", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String updateStatus(HttpServletRequest request, HttpServletResponse response, 
									  @RequestParam(value = "email" ) String email,
									  @RequestParam(value = "status") String status) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setEmail(email);
		user.setStatus(status);
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doUpdateStatus(user);
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/search", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String search(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "rowPerPage") Integer rowPerPage,
							  @RequestParam(value = "page") Integer page,
							  @RequestParam(value = "name", required=false) String name,
							  @RequestParam(value = "nim_nip", required=false) String nim,
							  @RequestParam(value = "role", required=false) String role,
							  @RequestParam(value = "status", required=false) String status,
							  @RequestParam(value = "description", required=false) String description) {
		Gson gson = new Gson();
		UserModel model = new UserModel();
		
		model.setRowPerPage(rowPerPage);
		model.setLimit(rowPerPage);
		model.setPage(page);
		model.setName(name);
		model.setNim(nim);
		model.setRoleId(role);
		model.setStatus(status);
		model.setDescription(description);
		
		Response responses = userService.doSearch(model);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getUserByEmail", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String getUserByEmail(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "email", required=true) String email) {
		Gson gson = new Gson();
		Response responses = userService.doGetUserById(email);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/searchRegisteredUser", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String searchRegistered(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "rowPerPage") Integer rowPerPage,
							  @RequestParam(value = "page") Integer page,
							  @RequestParam(value = "name", required=false) String name,
							  @RequestParam(value = "nim", required=false) String nim,
							  @RequestParam(value = "description", required=false) String description) {
		Gson gson = new Gson();
		UserModel model = new UserModel();
		
		model.setRowPerPage(rowPerPage);
		model.setLimit(rowPerPage);
		model.setPage(page);
		model.setName(name);
		model.setNim(nim);
		model.setDescription(description);
		model.setStatus("ST2");
		
		Response responses = userService.doSearch(model);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getUserById", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String getUserById(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "idUser", required=true) String idUser) {
		Gson gson = new Gson();
		
		Response responses = userService.doGetUserById(idUser);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/approve", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String approve(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "email", required=true) String email) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setEmail(email);
		user.setStatus("ST1");
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doApproveUser(user);
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/reject", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String reject(HttpServletRequest request,
							  HttpServletResponse response, 
							  @RequestParam(value = "email", required=true) String email) {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		
		user.setEmail(email);
		user.setStatus("ST3");
		user.setUpdatedDt(Constanta.sdf.format(new Date()));
		Response resp = null;
		
		resp = userService.doUpdateStatus(user);
		
		return gson.toJson(resp);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getcombo", method = RequestMethod.GET, produces = "application/json")
	public String autocomplate(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = userService.getCombo();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getComboProdi", method = RequestMethod.GET, produces = "application/json")
	public String getComboProdi(HttpServletRequest request,
							  HttpServletResponse response,
							  @RequestParam(value = "majorsId", required=false) String majorsId) {
		Gson gson = new Gson();
		Response responses = userService.getComboProdi(majorsId);
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getComboJurusan", method = RequestMethod.GET, produces = "application/json")
	public String getComboJurusan(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = userService.getComboJurusan();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getComboRole", method = RequestMethod.GET, produces = "application/json")
	public String getComboRole(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = userService.getComboRole();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getComboStatus", method = RequestMethod.GET, produces = "application/json")
	public String getComboStatus(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = userService.getComboStatus();
		return gson.toJson(responses);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/user/getComboDivision", method = RequestMethod.GET, produces = "application/json")
	public String getComboDivision(HttpServletRequest request,
							  HttpServletResponse response) {
		Gson gson = new Gson();
		Response responses = userService.getComboDivision();
		return gson.toJson(responses);
	}
	//end api

}
