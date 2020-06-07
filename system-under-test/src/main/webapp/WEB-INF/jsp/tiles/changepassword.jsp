<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.id.perpus.user.UserModel" %>
<% UserModel dto = (UserModel) request.getSession().getAttribute("userModel");%>
<!-- <section class="content-header"> -->
<!-- 	<h1>Change Password</h1> -->
<!-- 	<ol class="breadcrumb"> -->
<!-- 		<li><a href="javascript:void(0);"><i class="fa fa-dashboard"></i> -->
<!-- 				Beranda</a></li> -->
<!-- 	</ol> -->
<!-- </section> -->
<section class="content">

	<div class="box">
		<div class="box-header with-border" align="center">
			<h3 class="box-title">
				CHANGE PASSWORD
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Old Password</label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="password" class="form-control" id="password"
								name="password" placeholder="Old Password" tabindex="12"
								maxlength="50" required="required">
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">New Password</label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="password" class="form-control" id="newPassword"
								name="newPassword" placeholder="New Password" tabindex="12"
								maxlength="50" required="required">
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Confirm Password</label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="password" class="form-control" id="confirmPassword"
								name="confirmPassword" placeholder="Confirm Password" tabindex="12"
								maxlength="50" required="required">
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					</div>
					<div class="col-md-8">
						<button type="button" class="btn btn-default" id="btnSubmit"
							tabindex="18">
							SUBMIT
						</button>
						<button type="button" class="btn btn-default" id="btnReset"
							tabindex="18">
							RESET
						</button>
						<button type="button" class="btn btn-default" id="btnCancel"
							tabindex="18">
							CANCEL
						</button>
					</div>
					
				</div>
			<!-- </form> -->
		</div>
	</div>
</section>
	
<script src="${pageContext.request.contextPath}/assets/d3Js/d3.v3.min.js"></script>
<script type="text/javascript">
	function validatePassword(password) {
		var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]).{8,10}$/;
		return re.test(password);
	}
	function update(){
		$
		.ajax({
			url : "${pageContext.request.contextPath}/api/user/changepassword",
			type : "post",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			data : {
				"email" : "<%= dto.getEmail() %>",
				"password" : $('#newPassword').val()
			},
			success : function(response) {
				var message = response.message;
				var code = response.message;
				var status = response.status;
				if(status){
					setTimeout(function(){ window.location="${pageContext.request.contextPath}/login"; }, 1000);	
				}
				showMessage(code, message);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, errorThrown);
			}
		});
	}
	$("#btnSubmit").click(function(){
		if(validatePassword($('#newPassword').val())){
			if($('#newPassword').val()==$('#confirmPassword').val()){
				update();
			}
			else{
				showMessage("MSTD0031AERR", "New Password retype is not same");
			}
		}
		else{
			showMessage("MSTD0031AERR", "Password should contain at least one capital letter, number, and symbol (#,$,^,+,=,!,(),@,%,&) and 8 characters");
		}
	});
	
	$("#btnCancel").click(function(){
		window.location.replace('${pageContext.request.contextPath}/profile');
	});
	$("#btnReset").click(function(){
		$("#password").val("");
		$("#newPassword").val("");
		$("#confirmPassword").val("");
	});
	
	
</script>