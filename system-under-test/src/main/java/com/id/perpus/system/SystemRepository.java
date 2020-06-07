package com.id.perpus.system;

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
public class SystemRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SystemModel> getDataSystem(String sysCat, String sysSubCat) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" s.sys_cat,  ")
			 .append(" s.sys_sub_cat,  ")
			 .append(" s.sys_cd,  ")
			 .append(" s.sys_val,  ")
			 .append(" s.description,  ")
			 .append(" s.created_by,  ")
			 .append(" s.created_dt,  ")
			 .append(" s.updated_by,  ")
			 .append(" s.updated_dt  ")
			 .append(" FROM tb_m_system s  ")
			 .append(" WHERE s.sys_cat = ? ")
			 .append(" AND s.sys_sub_cat = ? ");
		
		List<SystemModel> datas = new ArrayList<SystemModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),sysCat, sysSubCat);
		for (Map<String, Object> row : rows) {
			SystemModel system = new SystemModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			system.setCreatedBy(Common.toString(row.get("created_by")));
			system.setCreatedDt(Constanta.sdf.format(createdDate));
			system.setUpdatedBy(Common.toString(row.get("updated_by")));
			system.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			system.setSysCat(Common.toString(row.get("sys_cat")));
			system.setSysSubCat(Common.toString(row.get("sys_sub_cat")));
			system.setSysCd(Common.toString(row.get("sys_cd")));
			system.setSysVal(Common.toString(row.get("sys_val")));
			system.setDescription(Common.toString(row.get("description")));
			
			datas.add(system);
		}
		
		return datas;
	}
	
	public SystemModel getDataSystem(String sysCat, String sysSubCat, String sysCd) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" s.sys_cat,  ")
			 .append(" s.sys_sub_cat,  ")
			 .append(" s.sys_cd,  ")
			 .append(" s.sys_val,  ")
			 .append(" s.description,  ")
			 .append(" s.created_by,  ")
			 .append(" s.created_dt,  ")
			 .append(" s.updated_by,  ")
			 .append(" s.updated_dt  ")
			 .append(" FROM tb_m_system s  ")
			 .append(" WHERE s.sys_cat = ? ")
			 .append(" AND s.sys_sub_cat = ? ")
			 .append(" AND s.sys_cd = ? ");
		
		SystemModel system = null;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),sysCat, sysSubCat, sysCd);
		for (Map<String, Object> row : rows) {
			system = new SystemModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			system.setCreatedBy(Common.toString(row.get("created_by")));
			system.setCreatedDt(Constanta.sdf.format(createdDate));
			system.setUpdatedBy(Common.toString(row.get("updated_by")));
			system.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			system.setSysCat(Common.toString(row.get("sys_cat")));
			system.setSysSubCat(Common.toString(row.get("sys_sub_cat")));
			system.setSysCd(Common.toString(row.get("sys_cd")));
			system.setSysVal(Common.toString(row.get("sys_val")));
			system.setDescription(Common.toString(row.get("description")));
		}
		
		return system;
	}
}
