package com.id.perpus.majors;

import com.id.perpus.common.BaseModel;

public class MajorsModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String majorsId;
	private String majorsName;
	
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
