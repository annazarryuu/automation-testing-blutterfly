package com.id.perpus.majors;

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
public class MajorsRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<MajorsModel> getAllMajors() throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" m.majors_id,  ")
			 .append(" m.majors_name,  ")
			 .append(" m.created_by,  ")
			 .append(" m.created_dt,  ")
			 .append(" m.updated_by,  ")
			 .append(" m.updated_dt  ")
			 .append(" FROM tb_m_majors m ");
		
		List<MajorsModel> datas = new ArrayList<MajorsModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString());
		for (Map<String, Object> row : rows) {
			MajorsModel prodi = new MajorsModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			prodi.setCreatedBy(Common.toString(row.get("created_by")));
			prodi.setCreatedDt(Constanta.sdf.format(createdDate));
			prodi.setUpdatedBy(Common.toString(row.get("updated_by")));
			prodi.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			prodi.setMajorsId(Common.toString(row.get("majors_id")));
			prodi.setMajorsName(Common.toString(row.get("majors_name")));
			
			datas.add(prodi);
		}
		
		return datas;
	}
	
	public MajorsModel getMajorsById(String majorsId) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" m.majors_id,  ")
			 .append(" m.majors_name,  ")
			 .append(" m.created_by,  ")
			 .append(" m.created_dt,  ")
			 .append(" m.updated_by,  ")
			 .append(" m.updated_dt  ")
			 .append(" FROM tb_m_majors m ")
			 .append(" WHERE m.majors_id = ?");
		
		MajorsModel majors = null;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(), majorsId);
		for (Map<String, Object> row : rows) {
			majors = new MajorsModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			majors.setCreatedBy(Common.toString(row.get("created_by")));
			majors.setCreatedDt(Constanta.sdf.format(createdDate));
			majors.setUpdatedBy(Common.toString(row.get("updated_by")));
			majors.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			majors.setMajorsId(Common.toString(row.get("majors_id")));
			majors.setMajorsName(Common.toString(row.get("majors_name")));
			
		}
		
		return majors;
	}
}
