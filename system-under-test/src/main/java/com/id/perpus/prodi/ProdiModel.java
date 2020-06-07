package com.id.perpus.prodi;

import com.id.perpus.common.BaseModel;

public class ProdiModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodiId;
	private String prodiName;
	private String majorsId;
	private String majorsName;
	public String getProdiId() {
		return prodiId;
	}
	public void setProdiId(String prodiId) {
		this.prodiId = prodiId;
	}
	public String getProdiName() {
		return prodiName;
	}
	public void setProdiName(String prodiName) {
		this.prodiName = prodiName;
	}
	public String getMajorsId() {
		return majorsId;
	}
	public void setMajorsId(String majorsId) {
		this.majorsId = majorsId;
	}
	public String getMajorsName() {
		return majorsName;
	}
	public void setMajorsName(String majorsName) {
		this.majorsName = majorsName;
	}
	
	
	
}
