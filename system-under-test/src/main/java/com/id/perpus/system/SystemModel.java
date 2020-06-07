package com.id.perpus.system;

import com.id.perpus.common.BaseModel;

public class SystemModel  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysCat;
	private String sysSubCat;
	private String sysCd;
	private String sysVal;
	private String description;
	
	public String getSysCat() {
		return sysCat;
	}
	public void setSysCat(String sysCat) {
		this.sysCat = sysCat;
	}
	public String getSysSubCat() {
		return sysSubCat;
	}
	public void setSysSubCat(String sysSubCat) {
		this.sysSubCat = sysSubCat;
	}
	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	public String getSysVal() {
		return sysVal;
	}
	public void setSysVal(String sysVal) {
		this.sysVal = sysVal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
