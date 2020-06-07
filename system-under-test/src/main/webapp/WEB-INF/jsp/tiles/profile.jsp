<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.id.perpus.user.UserModel" %>
<% UserModel dto = (UserModel) request.getSession().getAttribute("userModel");%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<form id="form-id" onsubmit="return false">
						<input type="hidden" id="stFormAction" name="stFormAction">
						<input type="hidden" id="userId" name="userId">
						
								<div class="modal-header" align="center">
									<h4 class="modal-title">USER PROFILE</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-lg-2" align="center" style="padding-top: 7px;">
												<img src="<%= dto.getProfilePicture() %>" height="100px" width="100px">
											</div>
											<div id="dataMember">
												<div class="col-lg-10">
													<div class="col-lg-2" style="padding-top: 7px;">
														Name 
														<br>
														Email
														<br>
														NIM
														<br>
														Major
														<br>
														Prodi
													</div>
													<div class="col-lg-8" style="padding-top: 7px;">
														: <%= dto.getName() %>
														<br>
														: <%= dto.getEmail() %>
														<br>
														: <%= dto.getNim() %>
														<br>
														: <%= dto.getJurusan() %>
														<br>
														: <%= dto.getProgramStudi() %>
													</div>
												</div>
											</div>
											<div id="dataStaff">
												<div class="col-lg-10">
													<div class="col-lg-2" style="padding-top: 7px;">
														Name 
														<br>
														Email
														<br>
														NIP
														<br>
														Staff
													</div>
													<div class="col-lg-8" style="padding-top: 7px;">
														: <%= dto.getName() %>
														<br>
														: <%= dto.getEmail() %>
														<br>
														: <%= dto.getNip() %>
														<br>
														: <%= dto.getDivision() %>
													</div>
												</div>
											</div>
											<div id="dataAdmin">
												<div class="col-lg-10">
													<div class="col-lg-2" style="padding-top: 7px;">
														Name 
														<br>
														Email
													</div>
													<div class="col-lg-8" style="padding-top: 7px;">
														: <%= dto.getName() %>
														<br>
														: <%= dto.getEmail() %>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row" style="padding-top: 10px;">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-2">
												<button type="button" class="btn btn-default" id="btnUploadProfilePicture"
												tabindex="5">
												UPLOAD PHOTO
												</button>
											</div>
											<div class="col-lg-2">
												<button type="button" class="btn btn-default" id="btnChangePassword"
												tabindex="5">
												CHANGE PASSWORD
												</button>
											</div>
											<div class="col-lg-2">
												<button type="button" class="btn btn-default" id="btnChangeProfile"
												tabindex="5">
												CHANGE PROFILE
												</button>
											</div>
											
										</div>
									</div>
										
					</form>
					<!-- /.box-body -->
				</div>
			</div>
		</div>
	</div>
</section>



<script type="text/javascript">
$(document).ready(function() {
	if("<%=dto.getRoleId()%>" == "OFC"){
		$("#dataAdmin").hide();
		$("#dataMember").hide();
		$("#dataStaff").show();
	}else if("<%=dto.getRoleId()%>" == "MBR"){
		$("#dataMember").show();
		$("#dataAdmin").hide();
		$("#dataStaff").hide();
	}else{
		$("#dataMember").hide();
		$("#dataAdmin").show();
		$("#dataStaff").hide();
	}
});
	$("#btnUploadProfilePicture").click(function() {
		window.location="${pageContext.request.contextPath}/uploadProfilePicture";		
	});
	
	$("#btnChangePassword").click(function() {
		window.location="${pageContext.request.contextPath}/changePassword";
	});
	$("#btnChangeProfile").click(function() {
		window.location="${pageContext.request.contextPath}/changeProfile";
	});
	
	

	
</script>