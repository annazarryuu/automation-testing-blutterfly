
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
  			<h2>Sistem Perpustakaan POLBAN</h2>
  			<br>
  		</div>
  	</div>
    <form action="${pageContext.request.contextPath}/login" id="loginForm" method="post">
      
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	User Name
	      </div>
	      <div class="col-lg-9">
	        <input type="text" class="form-control" id="username" name="username" placeholder="email@mail.com">
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
      	<div class="col-lg-3" style="padding-top: 7px;">
	  		Password
        </div>
        <div class="col-lg-9">
	      <input type="password" class="form-control" id="password" name="password" placeholder="">
      	</div>
      </div>
      <div class="row" style="margin-bottom: 10px;">
        <div class="col-lg-3">
        </div>
        <div class="col-lg-2">
         <button type="button" id="btnLogin" name="btnLogin" class="btn btn-default btn-block btn-flat">LOGIN</button>
        </div>
        <div class="col-lg-2">
         <button type="button"  onClick="reset()" class="btn btn-default btn-block btn-flat">RESET</button>
        </div>
        <!-- /.col -->
        <div class="col-lg-5">
          <button type="button" onClick="doForgot()" class="btn btn-default btn-block btn-flat">FORGOT PASSWORD</button>
        </div>
        <!-- /.col -->
      </div>
      <div class="row" style="margin-bottom: 10px;">
        <div class="col-lg-3">
        </div>
        <div class="col-lg-9">
          <button  type="button" onClick="doRegistrasi()" class="btn btn-default btn-block btn-flat">REGISTRASI</button>
        </div>
      </div>
    </form>
  </div>
  <!-- /.login-box-body -->
</div>

<!-- <div class="modal fade" id="modal-gs" tabindex="-1" role="dialog"> -->
<!-- 	<form id="form-id" onsubmit="return false"> -->
<!--     <div class="modal-dialog modal-sm" role="document"> -->
<!--       <div class="modal-content"> -->
<!--       	<div class="modal-header"> -->
<!--           <h4 class="modal-title" id="defaultModalLabel">Lupa Kata Sandi?</h4> -->
<!--         </div> -->
<!--         <div class="modal-body"> -->
<!--           Email  -->
<!--       	  <div class="form-group has-feedback"> -->
<!--         	<input type="email" class="form-control" id="email" name="email" placeholder="Email" required><span class="fa fa-envelope-o form-control-feedback"></span> -->
<!--       	  </div> -->
<!--         </div> -->
<!--         <div class="modal-footer"> -->
<!--           <button type="button" class="btn btn-default" id="btnSend" name="btnSend"><i class="fa fa-envelope-o"></i> Kirim</button> -->
<!--           <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-mail-reply"></i> Kembali</button> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--     </form> -->
<!-- </div> -->



<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/dist/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/toastr/toastr.js"></script>
<script type="text/javascript">
$("#username").focus();

var message = "${model.message}";
var code = "${model.code}";
if(message != null && "" != message){
	showMessage(code, message);
}

$( "#password" ).keyup(function(event) {
	  if (event.keyCode === 13) {
		  doLogin();
	  }
});
	
function reset(){
	$('#username').val("");
	$('#password').val("");
	
}
function doForgot(){
	window.location="${pageContext.request.contextPath}/forgotPassword";
}
function doRegistrasi(){
	window.location="${pageContext.request.contextPath}/registrasi";
}
	
$("#forgot").click(function(){
	doForgot();
});

$("#btnLogin").click(function(){
	doLogin();
})
function doLogin(){
	if (doValidation()){
		document.getElementById("loginForm").submit();
	}
}

function doValidation(){
	var err = 0;
	if ($("#password").val() == ""){
		showMessage("MSTD0031AERR","Field Password should be filled in");
		err++;
	}
	if ($("#username").val() == ""){
		showMessage("MSTD0031AERR","Field User Name should be filled in");
		err++;
	}
	
	if(err == 0){
		return true;
	}else{
		return false;
	}
	
}




</script>
</body>
</html>

