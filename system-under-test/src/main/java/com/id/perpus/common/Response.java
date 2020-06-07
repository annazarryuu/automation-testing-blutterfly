package com.id.perpus.common;

import java.util.List;

public class Response {
	private boolean status;
	private String message;
	private String code;
	private List<?> data;
	private List<?> links;
	private Integer currentPage;
	private Integer totalPage;
	private Integer offset;
	private String jsonObject;

	public Response(boolean status, String message, String code, List<?> data,Integer currentPage,Integer totalPage,Integer offset) {
		this.status = status;
		this.message = message;
		this.code = code;
		this.data = data;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.offset = offset;
	}
	
	public Response(boolean status, String message, String code, List<?> data) {
		this.status = status;
		this.message = message;
		this.code = code;
		this.data = data;
	}
	
	public Response(List<?> links) {
		this.links = links;
	}
	
	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}



	public Integer getTotalPage() {
		return totalPage;
	}



	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}



	public Integer getOffset() {
		return offset;
	}



	public void setOffset(Integer offset) {
		this.offset = offset;
	}



	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	public List<?> getLinks() {
		return links;
	}

	public void setLinks(List<?> links) {
		this.links = links;
	}


}
