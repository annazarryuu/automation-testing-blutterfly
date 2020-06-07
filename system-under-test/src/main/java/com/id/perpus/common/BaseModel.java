package com.id.perpus.common;

import java.io.Serializable;

public class BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer page;
	private Integer rowPerPage = Constanta.LIMIT;
	private Integer offset;
	private Integer limit = Constanta.LIMIT;
	private String find;
	private String createdBy;
	private String createdDt;
	private String updatedBy;
	private String updatedDt;
	private String stFormAction;
	
	public String getStFormAction() {
		return stFormAction;
	}
	public void setStFormAction(String stFormAction) {
		this.stFormAction = stFormAction;
	}
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(String updatedDt) {
		this.updatedDt = updatedDt;
	}
	public Integer getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	
	
}
