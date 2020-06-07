
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
  		<div class="text-left">
  			<h2>Forgot Password Screen</h2>
  			<br>
  		</div>
  	</div>
    <form action="${pageContext.request.contextPath}/forgotPassword" method="post">
      
      <div class="row" style="margin-bottom: 10px;">
	      <div class="col-lg-3" style="padding-top: 7px;">
	      	Email
	      </div>
	      <div class="col-lg-9">
	        <input type="text" class="form-control" id="email" name="email" placeholder="email@mail.com" required>
	      </div>
      </div> 
      <div class="row" style="margin-bottom: 10px;">
        <div class="col-lg-3">
        </div>
        <div class="col-lg-3">
         <button type="button" id="btnSend" class="btn btn-default btn-block btn-flat">SUBMIT</button>
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
	
$("#btnSend").click(function(){
	if (doValidation()){
		$.ajax({
	        url: "${pageContext.request.contextPath}/api/auth/forgot",
	        type: "post",
	        data: {insertemail:$("#email").val()},
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
	if ($("#email").val() == ""){
		showMessage("MSTD0031AERR","Field Email should be filled in?");
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

