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
				CHANGE PROFILE
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Email</label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <%= dto.getEmail() %>
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Name</label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="text" class="form-control" id="name"
								name="name" placeholder="" tabindex="12"
								maxlength="50" >
						</div>
					</div>
				</div>
				<div id="formOfficial" style="display:none">
					<div class="row col-md-12">
							<div class="col-md-2">
							<label for="exampleInputEmail1">NIP </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
								type="text" class="form-control" id="nip"
								name="nip" placeholder="" tabindex="12"
								maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Staff </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<select class="form-control" id="divId" name="divId">
									<option value="" selected="selected">-- Select One--</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div id="formMember" style="display:none">
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">NIM </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
								type="text" class="form-control" id="nim"
								name="nim" placeholder="" tabindex="12"
								maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Majors </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<select class="form-control" id="majorsId" name="majorsId">
									<option value="" selected="selected">-- Select One --</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Prodi </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<select class="form-control" id="prodiId" name="prodiId">
									<option value="" selected="selected">-- Select One --</option>
								</select>
							</div>
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
$(document).ready(function() {
	fillComboRole();
	fillComboMajors();
	fillComboProdi("");
	fillComboDivision();
	setTimeout(function(){ initialData(); }, 1000);	
});
$("#majorsId").change(function(){
	fillComboProdi(this.value);
})

$("#btnSubmit").click(function(){
	if(doValidation()){
		doSave();
	}
})

function initialData(){
	$("#name").val("<%=dto.getName()%>");
	$("#nip").val("<%=dto.getNip()%>");
	$("#divId").val("<%=dto.getDivision()%>");
	$("#nim").val("<%=dto.getNim()%>");
	$("#majorsId").val("<%=dto.getMajorsId()%>");
	$("#prodiId").val("<%=dto.getProdiId()%>");
	if("<%=dto.getRoleId()%>" == "OFC"){
		$("#formMember").hide();
		$("#formOfficial").show();
	}else if("<%=dto.getRoleId()%>" == "MBR"){
		$("#formMember").show();
		$("#formOfficial").hide();
	}else{
		$("#formMember").hide();
		$("#formOfficial").hide();
	}
	
}
function doSave(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/updateProfile",
        type: "post",
        data: {	name:$("#name").val(),
        		email:"<%=dto.getEmail()%>",
        		role:"<%=dto.getRoleId()%>",
        		nim:$("#nim").val(),
        		majors:$("#majorsId").val(),
        		prodi:$("#prodiId").val(),
        		nip:$("#nip").val(),
        		division:$("#divId").val(),
        		mode: "EDIT" },
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	if (response.status){
        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/profile"; }, 1000);	
        	}
        	showMessage(response.code, response.message);
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}
$("#btnReset").click(function(){
	initialData()
// 	$("#name").val("");
// 	$("#email").val("");
// 	$("#roleId").val("");
// 	$("#nip").val("");
// 	$("#divId").val("");
// 	$("#nim").val("");
// 	$("#majorsId").val("");
// 	$("#prodiId").val("");
// 	$("#formMember").hide();
// 	$("#formOfficial").hide();
})
$("#btnCancel").click(function(){
	window.location="${pageContext.request.contextPath}/profile";	
})

function doValidation(){
	var err = 0;
	
	if ($("#name").val() == ""){
		showMessage("MSTD0031AERR","Field Name should be filled in");
		err++;
	}
    if ($("#roleId").val() == ""){
		showMessage("MSTD0031AERR","Field Role should be filled in");
		err++;
	}
	if ($("#roleId").val() == "OFC"){
		if ($("#nip").val() == ""){
			showMessage("MSTD0031AERR","Field NIP should be filled in");
			err++;
		}
		if ($("#divId").val() == ""){
			showMessage("MSTD0031AERR","Field Staff should be filled in");
			err++;
		}
	}else if ($("#roleId").val() == "MBR"){
		if ($("#nim").val() == ""){
			showMessage("MSTD0031AERR","Field NIM should be filled in");
			err++;
		}
		if ($("#majorsId").val() == ""){
			showMessage("MSTD0031AERR","Field Majors should be filled in");
			err++;
		}
		if ($("#prodiId").val() == ""){
			showMessage("MSTD0031AERR","Field Prodi should be filled in");
			err++;
		}
	}
	if(err == 0){
		return true;
	}else{
		return false;
	}
	
}
function fillComboRole(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/getComboRole",
        type: "get",
        data: {	},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Prodi
        	var data = response.data;
        	$('#roleId').empty()
        	$('#roleId').append('<option value="" selected="selected">-- Select One --</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#roleId').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}

function fillComboProdi(majorsIdData){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/getComboProdi",
        type: "get",
        data: {majorsId:majorsIdData},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Prodi
        	var data = response.data;
        	$('#prodiId').empty()
        	$('#prodiId').append('<option value="" selected="selected">-- Select One--</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#prodiId').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}

function fillComboMajors(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/getComboJurusan",
        type: "get",
        data: {	},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Jurusan
        	var data = response.data;
        	$('#majorsId').empty()
        	$('#majorsId').append('<option value="" selected="selected">-- Select One--</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#majorsId').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}

function fillComboDivision(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/getComboDivision",
        type: "get",
        data: {	},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Jurusan
        	var data = response.data;
        	$('#divId').empty()
        	$('#divId').append('<option value="" selected="selected">-- Select One--</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#divId').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}
	
</script>