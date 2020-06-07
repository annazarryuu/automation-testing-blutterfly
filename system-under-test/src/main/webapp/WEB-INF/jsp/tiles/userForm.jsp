<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- <section class="content-header"> -->
<!-- 	<h1>Pengembalian</h1> -->
<!-- 	<ol class="breadcrumb"> -->
<!-- 		<li><a href="javascript:void(0);"><i class="fa fa-dashboard"></i> -->
<!-- 				Navigation</a></li> -->
<!-- 		<li><a href="javascript:void(0);"><i class="fa fa-pie-chart"></i> -->
<!-- 				Transaksi Pengembalian</a></li> -->
<!-- 	</ol> -->
<!-- </section> -->
<section class="content">

	<div class="box">
		<div class="box-header with-border" align="center">
			<h3 class="box-title">
				FORM ${mode} USER
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Name <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="text" class="form-control" id="name"
								name="name" placeholder="" tabindex="12"
								maxlength="50">
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Email <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="text" class="form-control" id="email"
								name="email" placeholder="" tabindex="12"
								maxlength="50">
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Role <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							<select class="form-control" id="roleId" name="roleId">
								<option value="" selected="selected">-- Select One --</option>
							</select>
						</div>
					</div>
				</div>
				<div id="formOfficial" style="display:none">
					<div class="row col-md-12">
							<div class="col-md-2">
							<label for="exampleInputEmail1">NIP <span style="color:red;">*</span></label>
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
						<label for="exampleInputEmail1">Staff <span style="color:red;">*</span></label>
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
						<label for="exampleInputEmail1">NIM <span style="color:red;">*</span></label>
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
						<label for="exampleInputEmail1">Majors <span style="color:red;">*</span></label>
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
						<label for="exampleInputEmail1">Prodi <span style="color:red;">*</span></label>
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

$("#roleId").change(function(){
	if(this.value == "OFC"){
		$("#formMember").hide();
		$("#formOfficial").show();
	}else if(this.value == "MBR"){
		$("#formMember").show();
		$("#formOfficial").hide();
	}else{
		$("#formMember").hide();
		$("#formOfficial").hide();
	}
	
})

$("#btnSubmit").click(function(){
	if(doValidation()){
		doSave();
	}
})

function initialData(){
	$("#name").val("${userData.name}");
	$("#email").val("${userData.email}");
	$("#roleId").val("${userData.roleId}");
	$("#nip").val("${userData.nip}");
	$("#divId").val("${userData.division}");
	$("#nim").val("${userData.nim}");
	$("#majorsId").val("${userData.majorsId}");
	$("#prodiId").val("${userData.prodiId}");
	if ("${mode}" == "ADD"){
		$("#formMember").hide();
		$("#formOfficial").hide();
	}else{
		if($("#roleId").val() == "OFC"){
			$("#formMember").hide();
			$("#formOfficial").show();
		}else if($("#roleId").val() == "MBR"){
			$("#formMember").show();
			$("#formOfficial").hide();
		}else{
			$("#formMember").hide();
			$("#formOfficial").hide();
		}
	}
}
function doSave(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/save",
        type: "post",
        data: {	name:$("#name").val(),
        		email:$("#email").val(),
        		role:$("#roleId").val(),
        		nim:$("#nim").val(),
        		majors:$("#majorsId").val(),
        		prodi:$("#prodiId").val(),
        		nip:$("#nip").val(),
        		division:$("#divId").val(),
        		mode: "${mode}" },
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	if (response.status){
        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/user"; }, 1000);	
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
	window.location="${pageContext.request.contextPath}/user";	
})

function doValidation(){
	var err = 0;
	
	if ($("#name").val() == ""){
		showMessage("MSTD0031AERR","Field Name should be filled in");
		err++;
	}
	if ($("#email").val() == ""){
		showMessage("MSTD0031AERR","Field Email should be filled in");
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
