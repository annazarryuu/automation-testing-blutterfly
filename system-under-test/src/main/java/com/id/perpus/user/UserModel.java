package com.id.perpus.user;

import com.id.perpus.common.BaseModel;

public class UserModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nim;
	private String name;
	private String email;
	private String programStudi;
	private String jurusan;
	private String status;
	private String roleId;
	private String roleName;
	private String profilePicture;
	private String description;
	private String statusName;
	private String majorsId;
	private String prodiId;
	
	private String division;
	private String nip;
	
	// login
	private String idUser;
	private String username;
	private String password;
	
	
	// filter search
	private String searchText;
	
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramStudi() {
		return programStudi;
	}
	public void setProgramStudi(String programStudi) {
		this.programStudi = programStudi;
	}
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getJurusan() {
		return jurusan;
	}
	public void setJurusan(String jurusan) {
		this.jurusan = jurusan;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getMajorsId() {
		return majorsId;
	}
	public void setMajorsId(String majorsId) {
		this.majorsId = majorsId;
	}
	public String getProdiId() {
		return prodiId;
	}
	public void setProdiId(String prodiId) {
		this.prodiId = prodiId;
	}
	
	
	
}
