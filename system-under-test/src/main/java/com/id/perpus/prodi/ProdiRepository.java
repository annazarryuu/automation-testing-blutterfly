package com.id.perpus.prodi;

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
public class ProdiRepository {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;

		public List<ProdiModel> getProdiByMajorsId(String majorsId) throws Exception{
			StringBuilder query = new StringBuilder();
			query.append(" SELECT  ")
				 .append(" p.prodi_id,  ")
				 .append(" p.majors_id,  ")
				 .append(" p.prodi_name,  ")
				 .append(" p.created_by,  ")
				 .append(" p.created_dt,  ")
				 .append(" p.updated_by,  ")
				 .append(" p.updated_dt  ")
				 .append(" FROM tb_m_prodi p ");
				 if (majorsId != null && !majorsId.isEmpty()){
					 query.append(" WHERE p.majors_id = '"+majorsId+"'");
				 }
			
			List<ProdiModel> datas = new ArrayList<ProdiModel>();
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString());
			for (Map<String, Object> row : rows) {
				ProdiModel prodi = new ProdiModel();
				Date createdDate = (Date) row.get("created_dt");
				Date updatedDate = (Date) row.get("updated_dt");
				prodi.setCreatedBy(Common.toString(row.get("created_by")));
				prodi.setCreatedDt(Constanta.sdf.format(createdDate));
				prodi.setUpdatedBy(Common.toString(row.get("updated_by")));
				prodi.setUpdatedDt(Constanta.sdf.format(updatedDate));
				
				prodi.setProdiId(Common.toString(row.get("prodi_id")));
				prodi.setMajorsId(Common.toString(row.get("majors_id")));
				prodi.setProdiName(Common.toString(row.get("prodi_name")));
				
				datas.add(prodi);
			}
			
			return datas;
		}
		
		public ProdiModel getProdiById(String prodiId) throws Exception{
			StringBuilder query = new StringBuilder();
			query.append(" SELECT  ")
				 .append(" p.prodi_id,  ")
				 .append(" p.majors_id,  ")
				 .append(" p.prodi_name,  ")
				 .append(" p.created_by,  ")
				 .append(" p.created_dt,  ")
				 .append(" p.updated_by,  ")
				 .append(" p.updated_dt  ")
				 .append(" FROM tb_m_prodi p  ")
				 .append(" WHERE p.prodi_id = ?");
			
			ProdiModel prodi = null;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(), prodiId);
			for (Map<String, Object> row : rows) {
				prodi = new ProdiModel();
				Date createdDate = (Date) row.get("created_dt");
				Date updatedDate = (Date) row.get("updated_dt");
				prodi.setCreatedBy(Common.toString(row.get("created_by")));
				prodi.setCreatedDt(Constanta.sdf.format(createdDate));
				prodi.setUpdatedBy(Common.toString(row.get("updated_by")));
				prodi.setUpdatedDt(Constanta.sdf.format(updatedDate));
				
				prodi.setProdiId(Common.toString(row.get("prodi_id")));
				prodi.setMajorsId(Common.toString(row.get("majors_id")));
				prodi.setProdiName(Common.toString(row.get("prodi_name")));
			}
			
			return prodi;
		}
	
}
