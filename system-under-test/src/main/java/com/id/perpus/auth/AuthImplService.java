package com.id.perpus.auth;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Mail;
import com.id.perpus.common.Messages;
import com.id.perpus.common.Response;
import com.id.perpus.user.UserModel;
import com.id.perpus.user.UserRepository;

@Service
public class AuthImplService implements AuthService{

	private final Logger logger = LoggerFactory.getLogger(AuthImplService.class);
	
	@Autowired
	Messages messages;
	
	@Autowired
	DataSource dataSource;

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Mail mailer;
	
	public Response doLogin(String username, String password) {
		try {
			UserModel dto = new UserModel();
			dto.setUsername(username);
			dto.setEmail(username);
			dto.setPassword(Common.MD5(password));
			
			List<UserModel> login = loginRepository.doLogin(dto);
			if(login != null && login.size() > 0){
				if(login.get(0).getStatus().equals("ST1")){
					return new Response(Constanta.SUCCESS,"","",null);
				}else{
					return new Response(Constanta.FAILED,messages.find("message.MSTD0000AERR","USER NON ACTIVE"),"MSTD0000AERR",null);
				}
			}else{
				return new Response(Constanta.FAILED,messages.find("message.MSTD0043AERR","user name or password"),"MSTD0043AERR",null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED,messages.find("message.MSTD0043AERR","user name or password"),"MSTD0043AERR",null);
		}
	}

	@Override
	public Response doForgot(String insertemail) {
		try {
			String password = Common.generatePassword();
//			String password = Constanta.PASSWORD_DEFAULT;
			UserModel dto = new UserModel();
			dto.setPassword(Common.MD5(password));
			dto.setEmail(insertemail);
			dto.setUpdatedBy("System");
			dto.setUpdatedDt(Constanta.sdf.format(new Date()));
			int update = userRepository.doUpdatePassword(dto);
			List<UserModel> dataUser = userRepository.doSearchByUsername(insertemail);
			if(update > 0 && dataUser.size()>0){
				ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		        executor.execute(new Runnable() {
		            @Override
		            public void run() {
		                try {
		                	StringBuilder body = new StringBuilder();
		                	body.append("<b>Hello " + dataUser.get(0).getName() + "</b><br>")
		                	    .append("<br>")
		                	    .append("We heard you need new password because you forgot current password <br>")
		                	    .append("Here, new password to use web PERPUS POLBAN <br>")
		                	    .append("<br>")
		                	    .append("<h2 style='margin-left:4em'><b>" + password + "</b></h2>")
		                	    .append("<br>")
		                	    .append("link web application : http://localhost:8080/PerpustakaanWebApp<br>");
		                	    
		                	mailer.send(insertemail, Constanta.EMAIL_SUBJECT,body.toString());
		                } catch (IOException e) {
		                    logger.error(e.getMessage(), e);
		                } catch (Exception e) {
		                	logger.error(e.getMessage(), e);
						}
		            }
		        });
				return new Response(Constanta.SUCCESS,messages.find("message.MSTD0000AINF","Kata sandi baru anda telah terkirim ke email"),"MSTD0000AINF",null);
			}
			
			return new Response(Constanta.SUCCESS,messages.find("message.MSTD0000AERR","Email yang anda masukan tidak terdaftar"),"MSTD0000AERR",null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED,messages.find("message.MSTD0000AERR","Terjadi kesalah ketika mengirim email"),"MSTD0000AERR",null);
		}
	}

}
