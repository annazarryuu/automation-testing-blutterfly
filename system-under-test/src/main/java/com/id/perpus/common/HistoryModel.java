package com.id.perpus.common;

public class HistoryModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String screen;
	private String action;
	private String user;
	
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
