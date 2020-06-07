package com.id.perpus.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;

@Repository
public class RoleRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<RoleModel> getAllRoles() throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" r.role_id,  ")
			 .append(" r.role_name,  ")
			 .append(" r.description,  ")
			 .append(" r.created_by,  ")
			 .append(" r.created_dt,  ")
			 .append(" r.updated_by,  ")
			 .append(" r.updated_dt  ")
			 .append(" FROM tb_m_role r ");
		
		List<RoleModel> datas = new ArrayList<RoleModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString());
		for (Map<String, Object> row : rows) {
			RoleModel role = new RoleModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			role.setCreatedBy(Common.toString(row.get("created_by")));
			role.setCreatedDt(Constanta.sdf.format(createdDate));
			role.setUpdatedBy(Common.toString(row.get("updated_by")));
			role.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			role.setRoleId(Common.toString(row.get("role_id")));
			role.setRoleName(Common.toString(row.get("role_name")));
			role.setDescription(Common.toString(row.get("description")));
			datas.add(role);
		}
		
		return datas;
	}
	
	public RoleModel doGetRoleById(String roleId) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" r.role_id,  ")
			 .append(" r.role_name,  ")
			 .append(" r.description,  ")
			 .append(" r.created_by,  ")
			 .append(" r.created_dt,  ")
			 .append(" r.updated_by,  ")
			 .append(" r.updated_dt  ")
			 .append(" FROM tb_m_role r ")
		     .append(" WHERE r.role_id = ? ");
		RoleModel role = new RoleModel();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString() , roleId);
		if(rows != null && rows.size() > 0){
			for (Map<String, Object> row : rows) {
				Date createdDate = (Date) row.get("created_dt");
				Date updatedDate = (Date) row.get("updated_dt");
				role.setCreatedBy(Common.toString(row.get("created_by")));
				role.setCreatedDt(Constanta.sdf.format(createdDate));
				role.setUpdatedBy(Common.toString(row.get("updated_by")));
				role.setUpdatedDt(Constanta.sdf.format(updatedDate));
				
				role.setRoleId(Common.toString(row.get("role_id")));
				role.setRoleName(Common.toString(row.get("role_name")));
				role.setDescription(Common.toString(row.get("description")));
			}
		}else{
			role.setRoleId("NON");
			role.setRoleName("Tidak Memiliki Grup");
		}
		
		return role;
	}
	
	
}
