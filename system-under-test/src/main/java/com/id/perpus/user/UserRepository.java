package com.id.perpus.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int doRegitrasi(UserModel dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_m_user( ")
		 	 .append(" profile_picture, ")
		 	 .append(" user_email, ")
	         .append(" name, ")
		     .append(" password, ")
	         .append(" active_status, ")
	         .append(" role_id, ")
		     .append(" created_by, ")
		     .append(" created_dt, ")
		     .append(" updated_by, ")
		     .append(" updated_dt   ")
		     .append(" ) VALUES ( ")
		     .append(" 'data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABkAAD/4QMtaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QzhGOTFBRjI5REREMTFFNUI4MDJCQTY0RjNCODM3M0UiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QzhGOTFBRjM5REREMTFFNUI4MDJCQTY0RjNCODM3M0UiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDoyMEU2RDQ3RjlEREQxMUU1QjgwMkJBNjRGM0I4MzczRSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDoyMEU2RDQ4MDlEREQxMUU1QjgwMkJBNjRGM0I4MzczRSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pv/uAA5BZG9iZQBkwAAAAAH/2wCEAAEBAQEBAQEBAQEBAQEBAQEB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', ")// 1
			 .append(" ?, ")// 1
		     .append(" ?, ")// 2
		     .append(" ?, ")// 3 
		     .append(" ?, ")// 4
		     .append(" ?, ")// 5
		     .append(" ?, ")// 6
		     .append(" ?, ")// 7
		     .append(" ?, ")// 8
		     .append(" ? ) ");// 9
	
		int row = jdbcTemplate.update(query.toString(),
				dto.getUsername(), // 1
				dto.getName(),// 2 
				dto.getPassword(),// 3
				dto.getStatus(),// 4
				dto.getRoleId(),// 5
				dto.getCreatedBy(),// 6
				dto.getCreatedDt(), // 7
				dto.getUpdatedBy(), // 8
				dto.getUpdatedDt());// 9
		return row;
	}
	public int doSaveDataStudent(UserModel dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_m_students( ")
		 	 .append(" nim, ")
		 	 .append(" user_email, ")
	         .append(" prodi_id, ")
	         .append(" created_by, ")
		     .append(" created_dt, ")
		     .append(" updated_by, ")
		     .append(" updated_dt   ")
		     .append(" ) VALUES ( ")
		     .append(" ?, ")// 1
		     .append(" ?, ")// 2
		     .append(" ?, ")// 3 
		     .append(" ?, ")// 4
		     .append(" ?, ")// 5
		     .append(" ?, ")// 6
		     .append(" ? ) ");// 7
	
		int row = jdbcTemplate.update(query.toString(),
				dto.getNim(), // 1
				dto.getEmail(),// 2 
				dto.getProgramStudi(),// 3
				dto.getCreatedBy(),// 4
				dto.getCreatedDt(), // 5
				dto.getUpdatedBy(), // 6
				dto.getUpdatedDt());// 7
		return row;
	}
	
	@SuppressWarnings("rawtypes")
	public List<UserModel> doSearchByUsername(String username) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
		 .append(" u.user_email, ")
       .append(" u.name, ")
	     .append(" s.nim, ")
	     .append(" m.majors_name, ")
	     .append(" p.prodi_name, ")
	     .append(" m.majors_id, ")
	     .append(" p.prodi_id, ")
       .append(" u.active_status, ")
       .append(" u.role_id, ")
       .append(" r.role_name, ")
       .append(" e.nip, ")
       .append(" e.`div`, ")
       .append(" u.profile_picture, ")
       .append(" (select s.sys_val from tb_m_system s where s.sys_cat='USER' and s.sys_sub_cat='STATUS' and s.sys_cd = u.active_status) status_name, ")
       .append(" u.created_by, ")
	     .append(" u.created_dt, ")
	     .append(" u.updated_by, ")
	     .append(" u.updated_dt   ")
	     .append(" FROM tb_m_user u left join tb_m_students s on u.user_email = s.user_email ")
		 .append("      left join tb_m_employee e on u.user_email = e.user_email ")
		 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
		 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
		 .append("      left join tb_m_role r on r.role_id = u.role_id ")
		 .append(" WHERE 1=1 ")
		 .append(" AND u.user_email = ? ");
		
		List<UserModel> users = new ArrayList<UserModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString() , username);
		for (Map row : rows) {
			UserModel user = new UserModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			
			user.setEmail(Common.toString(row.get("user_email")));
			user.setName(Common.toString(row.get("name")));
			user.setNim(Common.toString(row.get("nim")));
			user.setJurusan(Common.toString(row.get("majors_name")));
			user.setProgramStudi(Common.toString(row.get("prodi_name")));
			user.setRoleId(Common.toString(row.get("role_id")));
			user.setRoleName(Common.toString(row.get("role_name")));
			user.setProfilePicture(Common.toString(row.get("profile_picture")));
			user.setDescription(Common.toString(row.get("description")));
			user.setStatusName(Common.toString(row.get("status_name")));
			user.setStatus(Common.toString(row.get("active_status")));
			user.setMajorsId(Common.toString(row.get("majors_id")));
			user.setProdiId(Common.toString(row.get("prodi_id")));
			user.setNip(Common.toString(row.get("nip")));
			user.setDivision(Common.toString(row.get("div")));
			
			user.setCreatedBy(Common.toString(row.get("created_by")));
			user.setCreatedDt(Constanta.sdf.format(createdDate));
			user.setUpdatedBy(Common.toString(row.get("updated_by")));
			user.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			
			users.add(user);
		}
		
		return users;
	}
	
	public int doUpdatePassword(UserModel dto) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_user set");
		sb.append(" password = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), dto.getPassword(),dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getEmail());
	}
	
	public int doUpdateProfilePicture(UserModel dto) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_user set");
		sb.append(" profile_picture = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), dto.getProfilePicture(),dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getEmail());
	}
	
	public List<UserModel> doSearch(UserModel dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" u.user_email, ")
	         .append(" u.name, ")
		     .append(" IFNULL( s.nim, e.nip) as nim, ")
		     .append(" m.majors_name, ")
		     .append(" p.prodi_name, ")
	         .append(" u.active_status, ")
	         .append(" u.role_id, ")
	         .append(" r.role_name, ")
	         .append(" u.profile_picture, ")
	         .append(" (select s.sys_val from tb_m_system s where s.sys_cat='USER' and s.sys_sub_cat='STATUS' and s.sys_cd = u.active_status) status_name, ")
	         .append(" IFNULL( (select s.sys_val from tb_m_system s where s.sys_cat='STAFF' and s.sys_sub_cat='DIVISI_STAFF' and s.sys_cd = e.div), ")
	         .append( "CONCAT(m.majors_name, ', ', p.prodi_name)) as description, ")
	         .append(" u.created_by, ")
		     .append(" u.created_dt, ")
		     .append(" u.updated_by, ")
		     .append(" u.updated_dt   ")
		     .append(" FROM tb_m_user u left join tb_m_students s on u.user_email = s.user_email ")
			 .append("      left join tb_m_employee e on u.user_email = e.user_email ")
			 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
			 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
			 .append("      left join tb_m_role r on r.role_id = u.role_id ")
			 .append(" WHERE 1=1 ");
		
		if(dto.getName() != null && !dto.getName().isEmpty()){
			query.append(" AND u.name LIKE '%"+dto.getName()+"%'");
		}	
		if(dto.getNim() != null && !dto.getNim().isEmpty()){
			query.append(" AND s.nim LIKE '%"+dto.getNim()+"%'");
		}
		if(dto.getRoleId() != null && !dto.getRoleId().isEmpty()){
			query.append(" AND u.role_id = '"+dto.getRoleId()+"' ");
		}
		if(dto.getStatus() != null && !dto.getStatus().isEmpty()){
			query.append(" AND u.active_status = '"+dto.getStatus()+"'  ");
		}
		if(dto.getDescription() != null && !dto.getDescription().isEmpty()){
			query.append(" AND ( p.prodi_name LIKE '%"+dto.getDescription()+"%'  ");
			query.append("  OR e.div in (select s.sys_cd from tb_m_system s where s.sys_cat = 'STAFF' and s.sys_sub_cat = 'DIVISI_STAFF' and s.sys_val LIKE '%"+dto.getDescription()+"%' ) ");
			query.append("  OR m.majors_name LIKE '%"+dto.getDescription()+"%'  ) ");
		}
		
		
		query.append(" ORDER  BY u.name ASC LIMIT ? OFFSET ?");
		List<UserModel> users = new ArrayList<UserModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),dto.getLimit(), dto.getOffset());
		for (Map<String, Object> row : rows) {
			UserModel user = new UserModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			
			user.setEmail(Common.toString(row.get("user_email")));
			user.setName(Common.toString(row.get("name")));
			user.setNim(Common.toString(row.get("nim")));
			user.setJurusan(Common.toString(row.get("majors_name")));
			user.setProgramStudi(Common.toString(row.get("prodi_name")));
			user.setRoleId(Common.toString(row.get("role_id")));
			user.setRoleName(Common.toString(row.get("role_name")));
			user.setProfilePicture(Common.toString(row.get("profile_picture")));
			user.setDescription(Common.toString(row.get("description")));
			user.setStatusName(Common.toString(row.get("status_name")));
			user.setStatus(Common.toString(row.get("active_status")));
			
			user.setCreatedBy(Common.toString(row.get("created_by")));
			user.setCreatedDt(Constanta.sdf.format(createdDate));
			user.setUpdatedBy(Common.toString(row.get("updated_by")));
			user.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			users.add(user);
		}
		
		return users;
	}
	
	@SuppressWarnings("rawtypes")
	public Long doCount(UserModel dto) throws Exception{
		Long count = (long)0;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) COUNT")
		 .append(" FROM tb_m_user u left join tb_m_students s on u.user_email = s.user_email ")
		 .append("      left join tb_m_employee e on u.user_email = e.user_email ")
		 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
		 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
		 .append("      left join tb_m_role r on r.role_id = u.role_id ")
		 .append(" WHERE 1=1 ");
		if(dto.getName() != null && !dto.getName().isEmpty()){
			query.append(" AND u.name LIKE '%"+dto.getName()+"%'");
		}	
		if(dto.getNim() != null && !dto.getNim().isEmpty()){
			query.append(" AND s.nim LIKE '%"+dto.getNim()+"%'");
		}
		if(dto.getRoleId() != null && !dto.getRoleId().isEmpty()){
			query.append(" AND u.role_id = '"+dto.getRoleId()+"' ");
		}
		if(dto.getStatus() != null && !dto.getStatus().isEmpty()){
			query.append(" AND u.active_status = '"+dto.getStatus()+"'  ");
		}
		if(dto.getDescription() != null && !dto.getDescription().isEmpty()){
			query.append(" AND ( p.prodi_name LIKE '%"+dto.getDescription()+"%'  ");
			query.append("  OR e.div in (select s.sys_cd from tb_m_system s where s.sys_cat = 'STAFF' and s.sys_sub_cat = 'DIVISI_STAFF' and s.sys_val LIKE '%"+dto.getDescription()+"%' ) ");
			query.append("  OR m.majors_name LIKE '%"+dto.getDescription()+"%'  ) ");
		}
		
	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString());
		for (Map row : rows) {
			count = ((Long)row.get("COUNT"));	
		}
		return count;
	}
	public UserModel doSearchById(String userId) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
		 .append(" u.user_email, ")
        .append(" u.name, ")
	     .append(" s.nim, ")
	     .append(" m.majors_name, ")
	     .append(" p.prodi_name, ")
	     .append(" m.majors_id, ")
	     .append(" p.prodi_id, ")
        .append(" u.active_status, ")
        .append(" u.role_id, ")
        .append(" r.role_name, ")
        .append(" e.nip, ")
        .append(" e.`div`, ")
        .append(" u.profile_picture, ")
        .append(" (select s.sys_val from tb_m_system s where s.sys_cat='USER' and s.sys_sub_cat='STATUS' and s.sys_cd = u.active_status) status_name, ")
        .append(" u.created_by, ")
	     .append(" u.created_dt, ")
	     .append(" u.updated_by, ")
	     .append(" u.updated_dt   ")
	     .append(" FROM tb_m_user u left join tb_m_students s on u.user_email = s.user_email ")
		 .append("      left join tb_m_employee e on u.user_email = e.user_email ")
		 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
		 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
		 .append("      left join tb_m_role r on r.role_id = u.role_id ")
		 .append(" WHERE 1=1 ")
		 .append(" AND u.user_email = ? ");
		
		
		UserModel user = null;

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(), userId);
		for (Map<String, Object> row : rows) {
			user = new UserModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			
			user.setEmail(Common.toString(row.get("user_email")));
			user.setName(Common.toString(row.get("name")));
			user.setNim(Common.toString(row.get("nim")));
			user.setJurusan(Common.toString(row.get("majors_name")));
			user.setProgramStudi(Common.toString(row.get("prodi_name")));
			user.setRoleId(Common.toString(row.get("role_id")));
			user.setRoleName(Common.toString(row.get("role_name")));
			user.setProfilePicture(Common.toString(row.get("profile_picture")));
			user.setDescription(Common.toString(row.get("description")));
			user.setStatusName(Common.toString(row.get("status_name")));
			user.setStatus(Common.toString(row.get("active_status")));
			user.setMajorsId(Common.toString(row.get("majors_id")));
			user.setProdiId(Common.toString(row.get("prodi_id")));
			user.setNip(Common.toString(row.get("nip")));
			user.setDivision(Common.toString(row.get("div")));
			
			user.setCreatedBy(Common.toString(row.get("created_by")));
			user.setCreatedDt(Constanta.sdf.format(createdDate));
			user.setUpdatedBy(Common.toString(row.get("updated_by")));
			user.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
		}
		
		return user;
	}
	
	public int doApprove(String idUser) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE tb_m_user  ")
		     .append(" SET active_status = 'ST1' ")
		     .append(" WHERE user_email = ?  ");
		
		int row = jdbcTemplate.update(query.toString(),idUser);// 17
		return row;
	}
	
	public List<ComboModel> getCombo(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT user_email as value, name AS label FROM tb_m_user ");
		List<ComboModel> dtos = new ArrayList<ComboModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map<String, Object> row : rows) {
			ComboModel model = new ComboModel();
			model.setValue(Common.toString(row.get("value")));
			model.setLabel(Common.toString(row.get("label")));
			dtos.add(model);
		}
		
		return dtos;
	}
	public int doUpdateStatus(UserModel dto) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_user set");
		sb.append(" active_status = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), dto.getStatus(),dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getEmail());

	}
	public int doSaveDataEmployee(UserModel dto) {
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_m_employee( ")
		 	 .append(" nip, ")
		 	 .append(" user_email, ")
	         .append(" `div`, ")
	         .append(" created_by, ")
		     .append(" created_dt, ")
		     .append(" updated_by, ")
		     .append(" updated_dt   ")
		     .append(" ) VALUES ( ")
		     .append(" ?, ")// 1
		     .append(" ?, ")// 2
		     .append(" ?, ")// 3 
		     .append(" ?, ")// 4
		     .append(" ?, ")// 5
		     .append(" ?, ")// 6
		     .append(" ? ) ");// 7
	
		int row = jdbcTemplate.update(query.toString(),
				dto.getNip(), // 1
				dto.getEmail(),// 2 
				dto.getDivision(),// 3
				dto.getCreatedBy(),// 4
				dto.getCreatedDt(), // 5
				dto.getUpdatedBy(), // 6
				dto.getUpdatedDt());// 7
		return row;
	}
	public int doDeleteEmployee(UserModel user) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM tb_m_employee ");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), user.getEmail());

	}
	
	public int doDeleteStudent(UserModel user) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM tb_m_students ");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), user.getEmail());

	}
	
	public int doDeleteUser(UserModel user) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM tb_m_user ");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), user.getEmail());

	}
	public int doUpdateUser(UserModel dto) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_user set");
		sb.append(" name = ?,");
		sb.append(" role_id = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), dto.getName(),dto.getRoleId(),dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getEmail());

	}
	
	public int doUpdateStudents(UserModel dto) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_students set");
		sb.append(" nim = ?,");
		sb.append(" prodi_id = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), dto.getNim(),dto.getProgramStudi(),dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getEmail());

	}
	
	public int doUpdateEmployee(UserModel dto) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_employee set");
		sb.append(" nip = ?,");
		sb.append(" `div` = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE user_email = ?");
		return jdbcTemplate.update(sb.toString(), dto.getNip(),dto.getDivision(),dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getEmail());

	}
	public List<ComboModel> getComboNIM() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT user_email as value, nim AS label FROM tb_m_students ");
		List<ComboModel> dtos = new ArrayList<ComboModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map<String, Object> row : rows) {
			ComboModel model = new ComboModel();
			model.setValue(Common.toString(row.get("value")));
			model.setLabel(Common.toString(row.get("label")));
			dtos.add(model);
		}
		
		return dtos;
	}
}

