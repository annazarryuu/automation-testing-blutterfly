package com.id.perpus.user;

import com.id.perpus.common.Response;
import com.id.perpus.role.RoleModel;

public interface UserService {
	public Response doEdit(UserModel dto);
	public Response doRegistrasi(UserModel dto);
	public Response doUpdateProfilePicture(UserModel dto);
	public Response doUpdatePassword(UserModel dto);
	public Response doSearchByUsername(String username);
	public Response doUpdateStatus(UserModel dto);
	public Response doApproveUser(UserModel dto);
	
	// belum dipakai
	public RoleModel doGetRoleById(String idRole);
	
	// screen
	public Response doSearch(UserModel model);
	public Response doGetUserById(String userId);
	public UserModel doGetModelUserById(String userId);
	
	public Response getCombo();
	public Response getComboProdi(String majorsId);
	public Response getComboJurusan();
	public Response getComboRole();
	public Response getComboStatus();
	public Response getComboDivision();
	public Response doAdd(UserModel dto);
	public Response doDelete(UserModel user);
	
}

