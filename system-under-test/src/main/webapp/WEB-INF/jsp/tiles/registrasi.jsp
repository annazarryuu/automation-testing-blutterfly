
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Sistem Perpustakaan POLBAN</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" href="${pageContext.request.contextPath}/assets/dist/img/fav.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/toastr/toastr.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page" style="height: 525px;">
<div class="logo">
<%--     <img src="${pageContext.request.contextPath}/assets/dist/img/LogoBS.png" style="margin-left:50px;margin-top:10px;width:10%"> --%>
</div>
<div class="login-box" style="width: 560px;">

  <!-- /.login-logo -->
  <div class="login-box-body">
  	<div class="pull-center">
  		<div class="text-center">
  			<h2>REGISTRASI ANGGOTA PERPUSTAKAAN</h2>
  			<br>
  		</div>
  	</div>
    <form action="${pageContext.request.contextPath}/forgotPassword" id="registrasiForm" method="post">
      
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	Name
	      </div>
	      <div class="col-lg-9">
	        <input type="text" class="form-control" id="name" name="name" placeholder="" required>
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	Email
	      </div>
	      <div class="col-lg-9">
	        <input type="text" class="form-control" id="email" name="email" placeholder="email@mail.com" required>
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	NIM
	      </div>
	      <div class="col-lg-9">
	        <input type="text" class="form-control" id="nim" name="nim" placeholder="" required>
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	Jurusan
	      </div>
	      <div class="col-lg-9">
	        <select class="form-control" id="jurusan" name="jurusan"></select>
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	Prodi
	      </div>
	      <div class="col-lg-9">
	        <select class="form-control" id="prodi" name="prodi">
	        	<option value="" selected="selected">-- Select --</option>
	        </select>
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
        <div class="col-lg-3">
        </div>
        <div class="col-lg-3">
         <button type="button" id="btnSubmit"  class="btn btn-default btn-block btn-flat">SUBMIT</button>
        </div>
        <div class="col-lg-3">
         <button type="button" onClick="reset()" class="btn btn-default btn-block btn-flat">RESET</button>
        </div>
        <!-- /.col -->
        <div class="col-lg-3">
          <button type="button" onClick="cancel()" class="btn btn-default btn-block btn-flat">CANCEL</button>
        </div>
        <!-- /.col -->
      </div>
    </form>
  </div>
  <!-- /.login-box-body -->
</div>

<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/toastr/toastr.js"></script>
<script type="text/javascript">

$( document ).ready(function() {
	fillComboJurusan();
});

$("#username").focus();

var message = "${model.message}";
var code = "${model.code}";
if(message != null && "" != message){
	showMessage(code, message);
}

function reset(){
	$('#email').val("");	
}

function cancel(){
	window.location="${pageContext.request.contextPath}/login";	
}

$("#jurusan").change(function(){
	fillComboProdi(this.value);
})
$("#btnSubmit").click(function(){
	if (doValidation()){
		$.ajax({
	        url: "${pageContext.request.contextPath}/api/user/registrasi",
	        type: "post",
	        data: {	name:$("#name").val(),
	        		nim:$("#nim").val(),
	        		email:$("#email").val(),
	        		prodi:$("#prodi").val(),
	        		jurusan:$("#jurusan").val() },
	        contentType:"application/x-www-form-urlencoded",
	        dataType:"json",
	        success: function (response) {
	        	if (response.status){
	        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/login"; }, 1000);	
	        	}
	        	showMessage(response.code, response.message);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	           console.log(textStatus, errorThrown);
	        }
	    });
	}
})

function doValidation(){
	var err = 0;
	
	if ($("#prodi").val() == ""){
		showMessage("MSTD0031AERR","Field Prodi should be filled in");
		err++;
	}
	if ($("#jurusan").val() == ""){
		showMessage("MSTD0031AERR","Field Jurusan should be filled in");
		err++;
	}
	if ($("#nim").val() == ""){
		showMessage("MSTD0031AERR","Field NIM should be filled in");
		err++;
	}
	if ($("#email").val() == ""){
		showMessage("MSTD0031AERR","Field Email should be filled in");
		err++;
	}
	if ($("#name").val() == ""){
		showMessage("MSTD0031AERR","Field Name should be filled in");
		err++;
	}
	
	if(err == 0){
		return true;
	}else{
		return false;
	}
	
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
        	$('#prodi').empty()
        	$('#prodi').append('<option value="" selected="selected">-- Select --</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#prodi').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}

function fillComboJurusan(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/getComboJurusan",
        type: "get",
        data: {	},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Jurusan
        	var data = response.data;
        	$('#jurusan').empty()
        	$('#jurusan').append('<option value="" selected="selected">-- Select --</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#jurusan').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}


</script>
</body>
</html>

