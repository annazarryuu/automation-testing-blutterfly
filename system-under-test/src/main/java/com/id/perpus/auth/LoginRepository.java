package com.id.perpus.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.id.perpus.common.Common;
import com.id.perpus.user.UserModel;

@Repository
public class LoginRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("rawtypes")
	public List<UserModel> doLogin(UserModel dto) throws Exception{
		String sql = "SELECT user_email, active_status FROM tb_m_user WHERE user_email = ? AND password = ?";
		List<UserModel> users = new ArrayList<UserModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,dto.getEmail(), dto.getPassword());
		for (Map row : rows) {
			UserModel user = new UserModel();
			user.setEmail(Common.toString(row.get("user_email")));
			user.setStatus(Common.toString(row.get("active_status")));
			users.add(user);
		}
		return users;
	}
	
	public String doFoget(String email) throws Exception{
		String sql = "SELECT password FROM tb_m_user WHERE email = ?";
		String password = (String) jdbcTemplate.queryForObject(sql, new Object[] { email }, String.class);
		return password;
	}
}
