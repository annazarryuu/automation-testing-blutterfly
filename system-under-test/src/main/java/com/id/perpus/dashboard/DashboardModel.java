package com.id.perpus.dashboard;


import java.util.Date;

import com.id.perpus.common.BaseModel;

public class DashboardModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private float score;
	private Date count;
	private Long number;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getScore(){
		return score;
	}
	public void setScore(float score){
		this.score = score;
	}
	public Date getCountDt(){
		return count;
	}
	public void setCountDt(Date count){
		this.count = count;
	}
	public Long getNumber(){
		return number;
	}
	public void setNumber(Long number){
		this.number = number;
	}
	
}
