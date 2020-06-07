package com.id.perpus.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Mail;
import com.id.perpus.common.Messages;
import com.id.perpus.common.Response;
import com.id.perpus.majors.MajorsModel;
import com.id.perpus.majors.MajorsRepository;
import com.id.perpus.prodi.ProdiModel;
import com.id.perpus.prodi.ProdiRepository;
import com.id.perpus.role.RoleModel;
import com.id.perpus.role.RoleRepository;
import com.id.perpus.system.SystemModel;
import com.id.perpus.system.SystemRepository;

@Service
public class UserImplService implements UserService {

	@Autowired
	Messages messages;
	
	@Autowired
	Mail mailer;

	@Autowired
	DataSource dataSource;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private SystemRepository systemRepository;
	
	@Autowired
	private ProdiRepository prodiRepository;
	
	@Autowired
	private MajorsRepository majorsRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	private final Logger logger = LoggerFactory.getLogger(UserImplService.class);

	@Override
	public Response doRegistrasi(UserModel dto) {
		try {
			String password = Common.MD5(Constanta.PASSWORD_DEFAULT);
			dto.setPassword(password);
			dto.setRoleId("MBR");
			dto.setUsername(dto.getEmail());
			dto.setStatus("ST2"); 
			int result = repository.doRegitrasi(dto);
			result = result + repository.doSaveDataStudent(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", dto.getEmail()),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}
	
	@Override
	public Response doSearchByUsername(String username) {
		try {
			List<UserModel> list = repository.doSearchByUsername(username);
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",
					null);
		}
	}
	
	@Override
	public RoleModel doGetRoleById(String roleId) {
		try {
			return roleRepository.doGetRoleById(roleId);
		} catch (Exception e) {
			RoleModel g = new RoleModel();
			g.setRoleId("NON");
			g.setRoleName("Tidak Memiliki Grup");
			logger.error(e.getMessage(), e);
			return g;
		}
	}
	
	@Override
	public Response doSearch(UserModel dto) {
		try {
			int offset = 0;
			if (dto.getPage() != null) {
				offset = (dto.getPage() - 1) * dto.getRowPerPage();
			}

			dto.setOffset(offset);

			List<UserModel> list = repository.doSearch(dto);
			Long total_row = repository.doCount(dto);

			int totalPage = (int) Math.ceil(total_row / dto.getRowPerPage().doubleValue());
			Integer currentPage = ((offset / dto.getRowPerPage()) + 1);

			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,currentPage, totalPage, offset);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null, 0, 0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null, 0, 0, 1);
		}
	}
	
	@Override
	public Response doGetUserById(String userId) {
		try {
			List<UserModel> list = new ArrayList<UserModel>();
			UserModel data =repository.doSearchById(userId);
			if (data != null){
				list.add(data);
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,0,0,1);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null, 0, 0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null, 0, 0, 1);
		}
	}
	
	public UserModel doGetModelUserById(String userId) {
		try {
			UserModel data =repository.doSearchById(userId);
			return data;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Response doApproveUser(UserModel data) {
		try {
			int result = repository.doUpdateStatus(data);
			UserModel dataUser = repository.doSearchById(data.getEmail());
			if (result > 0) {
				ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		        executor.execute(new Runnable() {
		            @Override
		            public void run() {
		                try {
		                	StringBuilder body = new StringBuilder();
		                	body.append("<b>Hello " + dataUser.getName() + "</b><br>")
		                	    .append("<br>")
		                	    .append("Your registration account has been approved, please go to the link below  <br>")
		                	    .append("to use the POLBAN Library web <br>")
		                	    .append("<br>")
		                	    .append("link web application : http://localhost:8080/PerpustakaanWebApp")
		                	    .append("<br>")
		                	    .append("<hr>")
		                	    .append("<br>")
		                	    .append("Copyright © 2020 POLBAN Library, All rights reserved.<br>")
		                	    .append("Jl. Gegerkalong Hilir, Ciwaruga, Kec. Parongpong, Kabupaten Bandung Barat, Jawa Barat 40559<br>");
		                	    
		                	mailer.send(data.getEmail(), Constanta.EMAIL_SUBJECT,body.toString());
		                } catch (IOException e) {
		                    logger.error(e.getMessage(), e);
		                } catch (Exception e) {
		                	logger.error(e.getMessage(), e);
						}
		            }
		        });
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}
	
	@Override
	public Response getCombo() {
		try {
			List<ComboModel> list = repository.getCombo();
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}
	// send mail
	//this.mail.send(dto.getEmail(), Constanta.EMAIL_SUBJECT, "Your username is "+dto.getUsername()+" \n and password is "+newPassword);

	@Override
	public Response getComboProdi(String majorsId) {
		try {
			List<ProdiModel> data = prodiRepository.getProdiByMajorsId(majorsId);
			List<ComboModel> list = new ArrayList<ComboModel>();
			if (data != null){
				for(int i=0; i<data.size(); i++){
					ComboModel temp = new ComboModel();
					temp.setLabel(data.get(i).getProdiName());
					temp.setValue(data.get(i).getProdiId());
					list.add(temp);
				}
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}

	@Override
	public Response getComboJurusan() {
		try {
			List<MajorsModel> data = majorsRepository.getAllMajors();
			List<ComboModel> list = new ArrayList<ComboModel>();
			if (data != null){
				for(int i=0; i<data.size(); i++){
					ComboModel temp = new ComboModel();
					temp.setLabel(data.get(i).getMajorsName());
					temp.setValue(data.get(i).getMajorsId());
					list.add(temp);
				}
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}
	
	@Override
	public Response getComboRole() {
		try {
			List<RoleModel> data = roleRepository.getAllRoles();
			List<ComboModel> list = new ArrayList<ComboModel>();
			if (data != null){
				for(int i=0; i<data.size(); i++){
					ComboModel temp = new ComboModel();
					temp.setLabel(data.get(i).getRoleName());
					temp.setValue(data.get(i).getRoleId());
					list.add(temp);
				}
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}
	
	@Override
	public Response getComboStatus() {
		try {
			List<SystemModel> data = systemRepository.getDataSystem("USER", "STATUS");
			List<ComboModel> list = new ArrayList<ComboModel>();
			if (data != null){
				for(int i=0; i<data.size(); i++){
					ComboModel temp = new ComboModel();
					temp.setLabel(data.get(i).getSysVal());
					temp.setValue(data.get(i).getSysCd());
					list.add(temp);
				}
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}

	@Override
	public Response doUpdateProfilePicture(UserModel dto) {
		try {
			UserModel data = repository.doSearchById(dto.getEmail());
			dto.setUpdatedBy(data.getName());
			
			int result = repository.doUpdateProfilePicture(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
		
		}
	}

	@Override
	public Response doUpdatePassword(UserModel dto) {
		try {
			UserModel data = repository.doSearchById(dto.getEmail());
			dto.setUpdatedBy(data.getName());
			dto.setPassword(Common.MD5(dto.getPassword()));
			int result = repository.doUpdatePassword(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
		
		}
	}
	
	@Override
	public Response doUpdateStatus(UserModel dto) {
		try {
			UserModel data = repository.doSearchById(dto.getEmail());
			dto.setUpdatedBy(data.getName());
			dto.setPassword(Common.MD5(dto.getPassword()));
			int result = repository.doUpdateStatus(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
		
		}
	}

	@Override
	public Response getComboDivision() {
		try {
			List<SystemModel> data = systemRepository.getDataSystem("STAFF", "DIVISI_STAFF");
			List<ComboModel> list = new ArrayList<ComboModel>();
			if (data != null){
				for(int i=0; i<data.size(); i++){
					ComboModel temp = new ComboModel();
					temp.setLabel(data.get(i).getSysVal());
					temp.setValue(data.get(i).getSysCd());
					list.add(temp);
				}
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}

	@Override
	public Response doAdd(UserModel dto) {
		try {
			String password = Common.MD5(Constanta.PASSWORD_DEFAULT);
			dto.setPassword(password);
			dto.setUsername(dto.getEmail());
			dto.setStatus("ST1"); 
			int result = repository.doRegitrasi(dto);
			if ("MBR".equals(dto.getRoleId())){
				result = result + repository.doSaveDataStudent(dto);
			}else if ("OFC".equals(dto.getRoleId())){
				result = result + repository.doSaveDataEmployee(dto);
			}
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", dto.getEmail()),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}

	@Override
	public Response doEdit(UserModel dto) {
		try {
			UserModel previousData = repository.doSearchById(dto.getEmail());
			int result = repository.doUpdateUser(dto);
			if (previousData.getRoleId().equals(dto.getRoleId())){
				if ("MBR".equals(dto.getRoleId())){
					result = result + repository.doUpdateStudents(dto);
				}else if ("OFC".equals(dto.getRoleId())){
					result = result + repository.doUpdateEmployee(dto);
				}
			}else{
				// delete previous data
				result = result + repository.doDeleteStudent(dto);
				result = result + repository.doDeleteEmployee(dto);
				if ("MBR".equals(dto.getRoleId())){
					result = result + repository.doSaveDataStudent(dto);
				}else if ("OFC".equals(dto.getRoleId())){
					result = result + repository.doSaveDataEmployee(dto);
				}
			}
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}

	@Override
	public Response doDelete(UserModel user) {
		try {
			int result = repository.doDeleteEmployee(user);
			result = result + repository.doDeleteStudent(user);
			result = result + repository.doDeleteUser(user);
			
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	
	}

}
