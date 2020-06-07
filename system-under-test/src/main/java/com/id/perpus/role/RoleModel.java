package com.id.perpus.role;

import com.id.perpus.common.BaseModel;

public class RoleModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String description;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
