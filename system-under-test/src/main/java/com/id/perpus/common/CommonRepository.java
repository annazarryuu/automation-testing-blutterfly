package com.id.perpus.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> doFind(String table, String[] param, String field, String orderBy, String keyword, Integer page)
			throws Exception {
		int offset = 0;
		if (page != null) {
			offset = (page - 1) * Constanta.LIMIT;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+field+" FROM " + table +" ");
		if (keyword != null && !keyword.isEmpty()) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + param[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + param[i] + " LIKE '%" + keyword + "%' ");
					}
					
				}
			}
		}

		if (orderBy != null) {
			sb.append(orderBy);
		}

		sb.append(" LIMIT ? OFFSET ? ");
		return jdbcTemplate.queryForList(sb.toString(), Constanta.LIMIT, offset);
	}
	

	public List<Map<String, Object>> doFind(String query, String[] param, String orderBy, String keyword, Integer page)
			throws Exception {
		int offset = 0;
		if (page != null) {
			offset = (page - 1) * Constanta.LIMIT;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(query);
		if (keyword != null && !keyword.isEmpty()) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + param[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + param[i] + " LIKE '%" + keyword + "%' ");
					}
					
				}
			}
		}

		if (orderBy != null) {
			sb.append(orderBy);
		}

		sb.append(" LIMIT ? OFFSET ? ");
		return jdbcTemplate.queryForList(sb.toString(), Constanta.LIMIT, offset);
	}
	
	public List<Map<String, Object>> doFind(String query, String[] param, String orderBy, String keyword)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append(query);
		if (keyword != null && !keyword.isEmpty()) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + param[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + param[i] + " LIKE '%" + keyword + "%' ");
					}
					
				}
			}
		}

		if (orderBy != null) {
			sb.append(orderBy);
		}

		return jdbcTemplate.queryForList(sb.toString());
	}

	@SuppressWarnings("rawtypes")
	public Long doCount(String table, String[] field, String orderBy, String keyword, Integer page)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*)COUNT FROM " + table +" ");
		if (keyword != null && !keyword.isEmpty()) {
			if (field != null && field.length > 0) {
				for (int i = 0; i < field.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + field[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + field[i] + " LIKE '%" + keyword + "%' ");
					}
				}
			}
		}

		if (orderBy != null) {
			sb.append(orderBy);
		}

		Long count = (long)0;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map row : rows) {
			count = ((Long) row.get("COUNT"));
		}

		return count;
	}
	
	@SuppressWarnings("rawtypes")
	public Long doCount(String query, String[] field, String keyword, Integer page)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		if (keyword != null && !keyword.isEmpty()) {
			if (field != null && field.length > 0) {
				for (int i = 0; i < field.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + field[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + field[i] + " LIKE '%" + keyword + "%' ");
					}
				}
			}
		}

		Long count = (long)0;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map row : rows) {
			count = ((Long) row.get("COUNT"));
		}

		return count;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> doAutoComplate(String table, String[] param, String field, String keyword)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+field+" FROM " + table +" ");
		if (keyword != null && !keyword.isEmpty()) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + param[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + param[i] + " LIKE '%" + keyword + "%' ");
					}
				}
			}
		}

		List<String> result = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map row : rows) {
			for (String p : param) {
				String tmp = Common.toString((String)row.get(p)).toLowerCase();
				if(tmp != null && tmp.contains(Common.toString(keyword).toLowerCase())){
					if(!result.contains(Common.toString((String)row.get(p)))){
						result.add(Common.toString((String)row.get(p)));
					}
				}
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> doAutoComplate(String table, String[] param, String[] aliasParam, String field, String keyword)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+field+" FROM " + table +" ");
		if (keyword != null && !keyword.isEmpty()) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + param[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + param[i] + " LIKE '%" + keyword + "%' ");
					}
				}
			}
		}

		List<String> result = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map row : rows) {
			for (String p : aliasParam) {
				String tmp = Common.toString((String)row.get(p)).toLowerCase();
				if(tmp != null && tmp.contains(Common.toString(keyword).toLowerCase())){
					if(!result.contains(Common.toString((String)row.get(p)))){
						result.add(Common.toString((String)row.get(p)));
					}
				}
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> doAutoComplate(String query, String[] param, String keyword)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append(query);
		if (keyword != null && !keyword.isEmpty()) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if(i == 0){
						sb.append(" WHERE " + param[i] + " LIKE '%" + keyword + "%' ");
					}else{
						sb.append(" OR " + param[i] + " LIKE '%" + keyword + "%' ");
					}
				}
			}
		}

		List<String> result = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map row : rows) {
			for (String p : param) {
				String tmp = Common.toString((String)row.get(p)).toLowerCase();
				if(tmp != null && tmp.contains(Common.toString(keyword).toLowerCase())){
					if(!result.contains(Common.toString((String)row.get(p)))){
						result.add(Common.toString((String)row.get(p)));
					}
				}
			}
		}
		
		return result;
	}
}
