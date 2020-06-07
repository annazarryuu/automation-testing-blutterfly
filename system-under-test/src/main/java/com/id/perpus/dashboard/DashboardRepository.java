package com.id.perpus.dashboard;

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
public class DashboardRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("rawtypes")
	public List<DashboardModel> doSearch(DashboardModel dto) throws Exception{
		StringBuilder sb = new StringBuilder();
		//sb.append("SELECT user.name,test.scoreTest FROM test INNER JOIN testsession ON test.idTestSession = testsession.idTestSession INNER JOIN user ON testsession.idUser = user.idUser WHERE 1=1");
		sb.append("SELECT * FROM tb_m_user WHERE idRole = 2");
		sb.append(" ORDER BY totalScore DESC LIMIT ? OFFSET ?");

		List<DashboardModel> dtos = new ArrayList<DashboardModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString(),dto.getLimit(), dto.getOffset());
		for (Map row : rows) {
			DashboardModel model = new DashboardModel();
			
//			model.set
			model.setName((String)row.get("name"));
			model.setScore((Float)row.get("totalScore"));
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public Long doCount(DashboardModel dto) throws Exception{
		Long count = (long)0;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM edulevel WHERE 1=1 ");
		if(dto.getId() != null && !dto.getId().isEmpty()){
			sb.append(" AND idEduLevel LIKE '%"+dto.getId()+"%'");
		}
		
		if(dto.getName() != null && !dto.getName().isEmpty()){
			sb.append(" AND nmEduLevel LIKE '%"+dto.getName()+"%'");
		}
		count = jdbcTemplate.queryForObject(sb.toString(), Long.class);
		return count;
	}
	@SuppressWarnings("rawtypes")
	public List<DashboardModel> doCountDt(DashboardModel dto) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(user.createdDt) countApplicant FROM tb_m_user INNER JOIN role ON user.idRole = role.idRole WHERE role.idRole = 2");
		sb.append(" AND MONTH(user.createdDt) = 9 AND YEAR(user.createdDt) = YEAR(CURDATE())");
		sb.append(" ORDER BY user.createdDt ASC LIMIT ? OFFSET ?");
		List<DashboardModel> dtos = new ArrayList<DashboardModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString(),dto.getLimit(), dto.getOffset());
		for (Map row : rows) {
			DashboardModel model = new DashboardModel();
			//model.setNumber((Integer)row.get("createdDt"));
			model.setNumber((Long)row.get("countApplicant"));
			//model.setCountDt((Date)row.get("createdDt"));
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public int doAdd(DashboardModel dto) throws Exception{
		return jdbcTemplate.update("INSERT INTO edulevel(nmEduLevel,  createdBy, createdDt, updatedBy, updatedDt) VALUES (?,?,?,?,?)",
				dto.getName(), dto.getCreatedBy(), dto.getCreatedDt(), dto.getUpdatedBy(), dto.getUpdatedDt());

	}
	
	public int doEdit(DashboardModel dto) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE edulevel SET");
		sb.append(" nmEduLevel = ?,");
		sb.append(" updatedBy = ?,");
		sb.append(" updatedDt = ?");
		sb.append(" WHERE idEduLevel = ?");
		return jdbcTemplate.update(sb.toString(), dto.getName(), dto.getUpdatedBy(), dto.getUpdatedDt(), dto.getId());

	}
	
	@SuppressWarnings("rawtypes")
	public List<DashboardModel> doSearchById(String id) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM edulevel WHERE 1=1 AND idEduLevel = ?");
		List<DashboardModel> dtos = new ArrayList<DashboardModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString() , id);
		for (Map row : rows) {
			DashboardModel model = new DashboardModel();
			Date createdDate = (Date)row.get("createdDt");
			Date updatedDate = (Date)row.get("updatedDt");
			model.setId(Common.toString(row.get("idEduLevel")));
			model.setName((String)row.get("nmEduLevel"));
			model.setCreatedBy((String)row.get("createdBy"));
			model.setCreatedDt(Constanta.sdf.format(createdDate));
			model.setUpdatedBy((String)row.get("updatedBy"));
			model.setUpdatedDt(Constanta.sdf.format(updatedDate));
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public int doDelete(List<DashboardModel> dto) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM edulevel WHERE 1=2 ");
		for(DashboardModel data : dto){
			sb.append(" OR idEduLevel = "+data.getId());
		}
		return jdbcTemplate.update(sb.toString());
	}
}
