<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.id.perpus.common.JwtAuthenticationFilter" %>
<%@ page import="com.id.perpus.user.UserModel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Perpustakaan</title>
  <%
   String username = "";
   String name = "";
   String rolename = "Tidak Memiliki Grup";
   String userId ="";
   String role ="";
   String pp = "";
	try{
		String token = (String) session.getAttribute("token");
		System.out.print(token);
		System.out.print(token != null);
		if (token != null && JwtAuthenticationFilter.verifyJWT(token)) { 
			UserModel dto = (UserModel) request.getSession().getAttribute("userModel");
			
			username = dto.getEmail();
			name = dto.getName();
			role = request.getSession().getAttribute("role").toString();
			rolename = request.getSession().getAttribute("roleName").toString();
			if(!"".equals(dto.getProfilePicture())){
				pp = dto.getProfilePicture();
			}
		}else {
			%>
			<script type="text/javascript">
				window.location = "${pageContext.request.contextPath}/login";
			</script>
			<% 	
		}	
	}catch(Exception e){
		%>
		<script type="text/javascript">
			window.location = "${pageContext.request.contextPath}/login";
		</script>
		<% 
	}
%>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/toastr/toastr.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/select2.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/_auto.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/daterangepicker/daterangepicker.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/iCheck/all.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/colorpicker/bootstrap-colorpicker.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/timepicker/bootstrap-timepicker.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/material-design-preloader/md-preloader.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/daterangepicker/daterangepicker.css">
 <!--   DateTimepicker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker-standalone.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
  <!-- DataTable -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.bootstrap.css">
  <!-- Editor -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
  <!-- Plugins -->
   <!--  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webster/css/plugins-css.css"/>-->
  <!--  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webster/css/typography.css"/>-->
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webster/css/shortcodes/shortcodes.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webster/css/style.css"/> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webster/css/shop.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webster/css/responsive.css"/>
  

  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.2.61/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/dist/js/app.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/dist/js/auto.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/datepicker/bootstrap-datepicker.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/fastclick/fastclick.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/sparkline/jquery.sparkline.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/dist/js/demo.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/toastr/toastr.js"></script>
  <script src="${pageContext.request.contextPath}/assets/dist/js/custom.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/select2/select2.full.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/timepicker/bootstrap-timepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/daterangepicker/daterangepicker.js"></script>
  <!-- DatetimePicker -->
  <script src="${pageContext.request.contextPath}/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
  <!-- Data Table -->
  <script src="${pageContext.request.contextPath}/assets/plugins/datatables/jquery.dataTables.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.bootstrap.min.js"></script>
  

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
   <!-- <style>
  .ui-autocomplete { 
    max-width: 250px; 
     max-height: 400px; 
     overflow-y: auto;   /* prevent horizontal scrollbar */
    overflow-x: hidden; /* add padding to account for vertical scrollbar */
    z-index:1000 !important;
   } 
  
  
.node { 
	  cursor: pointer; 
} 
	
	.node circle {
  	fill: #fff;
	stroke: steelblue;
	stroke-width: 1.5px;
	   	}
	
.node text {
font: 14px sans-serif;
	}
	
.link {
  fill: none;
	  stroke: #ccc;
  stroke-width: 1.5px;
	} 
  </style> -->
  
<style>

.link {
  fill: none;
  stroke: #666;
  stroke-width: 1.5px;
}

.links line {
  stroke: #999;
  stroke-opacity: 0.6;
}

.nodes circle {
  stroke: #fff;
  stroke-width: 1.5px;
}

#licensing {
  fill: green;
}

.link.licensing {
  stroke: green;
}

.link.resolved {
  stroke-dasharray: 0,2 1;
}

circle {
  fill: #ccc;
  stroke: #333;
  stroke-width: 1.5px;
}

text {
  font: 10px sans-serif;
  pointer-events: none;
  text-shadow: 0 1px 0 #fff, 1px 0 0 #fff, 0 -1px 0 #fff, -1px 0 0 #fff;
}

a.disabled {
  pointer-events: none;
  cursor: default;
  color: #B0B0B0;
}

</style>

<!--  <style>
.link {
  fill: none;
  stroke: #999;
  stroke-width: 1.5px;
}

.links line {
  stroke: #999;
  stroke-opacity: 0.6;
}

.nodes circle {
  stroke: #fff;
  stroke-width: 1.5px;
}

text {
  font-family: sans-serif;
  font-size: 10px;
}

</style>
-->
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="page-loader-wrapper">
	<div class="loader">
            <div class="md-preloader pl-size-md">
                <svg viewbox="0 0 75 75">
                    <circle cx="37.5" cy="37.5" r="33.5" class="pl-red" stroke-width="4" />
                </svg>
            </div>
            <p>Harap Tunggu...</p>
    </div>
</div>
<div class="wrapper">

  <header class="main-header">

    <!-- Logo -->
<%--     <a class="logo" style="background-image: url(${pageContext.request.contextPath}/assets/dist/img/<%="latar2.jpg"%>)"> --%>
    <a class="logo" style="background-color:#BDD7EE">
      
      <!-- mini logo for sidebar mini 50x50 pixels -->
<!--       <span class="logo-mini"><</span> -->
<%-- 		<img src="${pageContext.request.contextPath}/assets/dist/img/<%="avatar.png"%>" alt="User Image" style="width:1000%; max-width: 100%"> --%>
<!--       logo for regular state and mobile devices -->
      <span class="logo-lg" style="font-size:15px; color:#000" >POLBAN LIBRARY SYSTEM</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
<%--     <nav class="navbar navbar-static-top" style="background-image: url(${pageContext.request.contextPath}/assets/dist/img/<%="latar.jpg"%>)"> --%>
    <nav class="navbar navbar-static-top" style="background-color:#BDD7EE">
      <!-- Sidebar toggle button-->
<!--       <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" style="background-color: #333"> -->
<!--         <span class="sr-only">Toggle navigation</span> -->
<!--       </a> -->
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
<!--             <a href="#" class="dropdown-toggle" data-toggle="dropdown"> -->
<!--               <i class="fa fa-bell"></i> -->
<!--               <span class="label label-warning" id="notificaion-count">0</span> -->
<!--             </a> -->
            <ul class="dropdown-menu">
              <li class="header" id="notification-head"></li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu" id="menu-notification">
                  
                </ul>
              </li>
              <li class="footer"><a href="${pageContext.request.contextPath}/notification">Tampilkan Semua</a></li>
            </ul>
          </li>
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
       			<span style="font-size:15px; color:#000" >USER PROFILE</span>
<%--               <img src="${pageContext.request.contextPath}/assets/dist/img/pp/<%=pp%>" width="20" height="20" class="img-circle" alt="User Image"> --%>
<%--               <span class="hidden-xs"><%=name%></span> --%>
            </a>
            <ul class="dropdown-menu" style="width: 152px;">
              <!-- User image -->
<!--               <li class="user-header"> -->
<%--                 <img src="${pageContext.request.contextPath}/assets/dist/img/pp/<%=pp%>" class="img-circle" alt="User Image"> --%>
<!--                 <p> -->
<%--                   <%=name%> - <%=rolename%> --%>
<!--                 </p> -->
<!--               </li> -->
              <!-- Menu Body -->
              
              <!-- Menu Footer-->
              <li>
                  <a href="${pageContext.request.contextPath}/profile"> PROFILE</a>
              </li>
              <li >
                  <a href="${pageContext.request.contextPath}/logout"> LOG OUT </a>
              </li>
            </ul>
          </li>
        </ul>
      </div>

    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar" style="background-color: #435468">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel" style="background-color:#435468">
        <div class="user-header" align="center">
          <img src="<%=pp%>" width="100" height="100"class="img-circle" alt="User Image">
          <p style="color:#fff;"><%=rolename%></p>
        </div>
<!--         <div class="pull-left info"> -->
<!--           <a href="#"><i class="fa fa-circle text-success"></i> Online</a> -->
<!--         </div> -->
      </div>
      <!-- sidebar menu: : style can be found in sidebar.less -->

      <ul class="sidebar-menu">
<!--       <li class="header">NAVIGASI UTAMA</li> -->
      	<li id="BFB10000">
          <a href="${pageContext.request.contextPath}/dash-board">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
          </a>
        </li>
      <% if ("ADM".equals(role)){ %>
        <li id="BFB10001">
          <a href="${pageContext.request.contextPath}/user">
            <i class="fa fa-dashboard"></i> <span>User Management</span>
          </a>
        </li>
      <% } %>
      <% if ("OFC".equals(role)){ %>
        <li id="BFB10002">
          <a href="${pageContext.request.contextPath}/book">
            <i class="fa fa-dashboard"></i> <span>Book Management</span>
          </a>
        </li>
        <li id="BFB10003">
        	<a href="${pageContext.request.contextPath}/userVerification">
        		<i class="fa fa-pie-chart"></i> <span>Member Verification</span> 
        	</a>
        </li>
        <li class="treeview" id="BFB20000">
          <a href="#">
            <i class="fa fa-wrench"></i>
            <span>Transaction</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
        	<li id="BFB20001"><a href="${pageContext.request.contextPath}/pinjam"><span>Loan Transaction</span></a></li>
         	<li id="BFB20002"><a href="${pageContext.request.contextPath}/kembali"><span>Return Transaction</span></a></li>
          </ul>
        </li>
        <% } %>
        <% if ("MBR".equals(role)){ %>
        <li id="BFB30001">
        	<a href="${pageContext.request.contextPath}/katalog">
        		<i class="fa fa-pie-chart"></i> <span>Book Catalog</span> 
        	</a>
        </li>
         <% } if ("OFC".equals(role) || "MBR".equals(role)){ %>
        <li id="BFB40001">
        	<a href="${pageContext.request.contextPath}/loanHistory">
        		<i class="fa fa-pie-chart"></i> <span>History</span> 
        	</a>
        </li>
        <% } %>
        <%-- <li class="treeview" id="BFB20000">
          <a href="#">
            <i class="fa fa-wrench"></i>
            <span>GRAPH KELUARGA</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
        	<li id="BFB80001"><a href="${pageContext.request.contextPath}/graph"><i class="fa fa-pie-chart"></i> <span>GRAPH KELUARGA</span></a></li>
         	<li id="BFB80002"><a href="${pageContext.request.contextPath}/graphV2"><i class="fa fa-area-chart"></i> <span>GRAPH KELUARGA V.2</span></a></li>
          </ul>
        </li> --%>
        <%-- <li id="BFB60000">
          <a href="${pageContext.request.contextPath}/question">
            <i class="glyphicon glyphicon-question-sign"></i> <span>BANTUAN</span>
          </a>
        </li> --%>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
  	<tiles:insertAttribute name="content" />
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div align="center" class=" hidden-xs">
      <b>Politeknik Negeri Bandung LIBRARY 2020</b>
    </div>
  </footer>
</div>
<div class="modal fade" id="alert-messages" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
      	<div class="modal-header">
          <h4 class="modal-title" id="defaultModalLabel"><div id="modal-header"></div></h4>
        </div>
        <div class="modal-body">
          <p align="center"><div id="modal-message"></div></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="btn-modal" name="btn-modal"><i class="glyphicon glyphicon-ok"></i> Yes</button>
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i> No</button>
        </div>
      </div>
    </div>
  </div>


<script type="text/javascript">
var userId = "<%=userId%>";
var role = "<%=role%>";
$(document).ready(function() {
	setTimeout(function () { $('.page-loader-wrapper').fadeOut(); }, 50);
});

function doNotification() {
	$.ajax({
		url : "${pageContext.request.contextPath}/api/notification/find",
		type : "post",
		contentType:"application/x-www-form-urlencoded",
		dataType : "json",
		data : {	userId  : userId,
					find : "",
					status: "U",
					page : 1
				},
		success : function(response) {
					var data = response.data;
					var notif = "";
					var count = 0;
					if(data != null && data.length > 0){
						for (var i = 0; i < data.length; i++) {
							var action = data[i]["action"];
							if(action != null && action != "" && action !="undefined"){
								action = data[i]["action"];
							}else{
								action = "";
							}
							
				        	if("R" == data[i]["status"]){
				        		notif += '<li><a onclick="doRead('+data[i]["id"]+')" href="${pageContext.request.contextPath}/'+action+'">'
	                      		+'<i class="fa fa-bell-o"></i>'+data[i]["message"]
	                    		+'</a>'
	                  			+'</li>';	
				        	}else{
				        		count++;
				        		notif += '<li><a onclick="doRead('+data[i]["id"]+')" href="${pageContext.request.contextPath}/'+action+'">'
	                      		+'<i class="fa fa-bell-o text-aqua"></i><b>'+data[i]["message"]
	                    		+'</b></a>'
	                  			+'</li>';
				        	}
				        }
					}
					
			        if(count < 1){
			        	$("#notification-head").html("<center>Anda tidak memiliki notifikasi baru</center>");
			        }else{
			        	$("#notification-head").html("<center>Anda memiliki "+count+" notifikasi baru</center>");	
			        	showMessage("MSTD0000AINF", getMessage(MSTD0000AINF,"Anda memiliki "+count+" notifikasi baru"));
			        }
			        
			        $("#notificaion-count").html(count);
					$("#menu-notification").html(notif);
				},
		error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus, errorThrown);
				}
		});
}

function doMenu() {
	$.ajax({
		url : "${pageContext.request.contextPath}/api/menu/menuByRole",
		type : "post",
		contentType:"application/x-www-form-urlencoded",
		dataType : "json",
		data : {	userId  : userId },
		success : function(response) {
					var data = response.data;
					if(data != null && data.length > 0){
						for (var i = 0; i < data.length; i++) {
					          var id = "#"+data[i]["functionId"];
					          $(id).remove();
					     }
					}
				},
		error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus, errorThrown);
				}
		});
}


function doMenuButton1(menu) {
	$("#"+menu).addClass('active');
	doSetButton(menu);
}


function doMenuButton2(parent, menu) {
	$("#"+parent).addClass('active');
	$("#"+menu).addClass('active');
	doSetButton(menu);
}

function doMenuButton3(parent, menu, submenu) {
	$("#"+parent).addClass('active');
	$("#"+menu).addClass('active');
	$("#"+submenu).addClass('active');
	doSetButton(submenu);
}

function doSetButton(menu){
	$.ajax({
		url : "${pageContext.request.contextPath}/api/button/getButtonByRole",
		type : "post",
		contentType:"application/x-www-form-urlencoded",
		dataType : "json",
		data : {userId:userId,menu:menu},
		success : function(response) {
					var data = response.data;
					if(data != null && data.length > 0){
						for (var i = 0; i < data.length; i++) {
					          var id = "#"+data[i]["buttonId"];
					          $(id).remove();
					     }
					}
				},
		error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus, errorThrown);
				}
		});
}

function logger(screen,action){
	$.ajax({
		url : "${pageContext.request.contextPath}/api/history/add",
		type : "post",
		contentType:"application/x-www-form-urlencoded",
		dataType : "json",
		data : {screen:screen, action:action},
		success : function(response) {
					
				},
		error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus, errorThrown);
				}
		});
}

$('.datepicker2').datepicker({autoclose: true, format: 'yyyy-mm-dd', daysOfWeekDisabled: [0,6]});
//$('.datetimepicker2').daterangepicker({autoclose: true, timePicker: true, format: 'yyyy-mm-dd HH:mm:SS A'});
$('.datetimepicker2').datetimepicker({
	//language: 'pt-BR'
    format: 'YYYY-MM-DD HH:mm:ss'
});


//window.setInterval(function(){
//	doNotification();
//}, 1 * 60 * 1000);

$('input[required], input[required="required"]').each(function(i, e){
	        e.oninput = function(el)
	        {
	            el.target.setCustomValidity("");
	        };

	        e.oninvalid = function(el)
	        {
	        	if (el.target.type == "email")
	            {
	        		el.target.setCustomValidity("Format email yang ada masukan salah");
	            }else{
	            	el.target.setCustomValidity("Kolom ini harus diisi!");	
	            }
	            
	        };
	    });

function doRead(id){
	$.ajax({
		url : "${pageContext.request.contextPath}/api/notification/read",
		type : "post",
		contentType:"application/x-www-form-urlencoded",
		dataType : "json",
		data : {id:id},
		success : function(response) {},
		error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus, errorThrown);
				}
		});
}

function loading(){
	setTimeout(function () { $('.page-loader-wrapper').fadeIn(); }, 5);
	
}
function loadout(){
	setTimeout(function () { $('.page-loader-wrapper').fadeOut(); }, 5);
}	 

function getCombo(path, idSelect, placeHolder) {
	$.ajax({
		url : "${pageContext.request.contextPath}/api/" + path
				+ "/getcombo",
		type : "get",
		dataType : "json",
		success : function(response) {
			var combo = response.data;
			var message = response.message;
			var code = response.message;
			var status = response.status;

			$(idSelect).empty();
			if (combo != null && combo.length > 0) {
				$(idSelect).append($('<option>', {
					value : '',
					text : placeHolder
				}));
				for (var i = 0; i < combo.length; i++) {
					var no = i + 1;
					$(idSelect).append($('<option>', {
						value : combo[i]['value'],
						text : combo[i]['label'],
					}));
				}

			} else {
				$(idSelect).append($('<option>', {
					value : '',
					text : placeHolder
				}));
			}
			logger("BFB20003", SEARCH);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
	
}
function getComboKey(path, idSelect, placeHolder, parameter) {
	$.ajax({
		url : "${pageContext.request.contextPath}/api/" + path
				+ "/getcombo",
		type : "get",
		data : {parameter:parameter},
		dataType : "json",
		success : function(response) {
			var combo = response.data;
			var message = response.message;
			var code = response.message;
			var status = response.status;

			$(idSelect).empty();
			if (combo != null && combo.length > 0) {
				$(idSelect).append($('<option>', {
					value : '',
					text : placeHolder
				}));
				for (var i = 0; i < combo.length; i++) {
					var no = i + 1;
					$(idSelect).append($('<option>', {
						value : combo[i]['value'],
						text : combo[i]['label'],
					}));
				}

			} else {
				$(idSelect).append($('<option>', {
					value : '',
					text : placeHolder
				}));
			}
			logger("BFB20003", SEARCH);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		}
	});
	
}
</script>
</body>
</html>
