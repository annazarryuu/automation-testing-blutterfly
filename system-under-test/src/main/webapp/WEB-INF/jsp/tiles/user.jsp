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
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<form id="form-id" onsubmit="return false">
						<input type="hidden" id="stFormAction" name="stFormAction">
						<input type="hidden" id="userId" name="userId">
						
								<div class="modal-header" align="center">
									<h4 class="modal-title">User Data Management</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-lg-1" style="padding-top: 7px;">
												Name
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="name"
														name="name" placeholder="">
												</div>
											</div>
											<div class="col-lg-1" style="padding-top: 7px;">
												NIM/NIP
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="nip"
														name="nip" placeholder="">
												</div>
											</div>
											<div class="col-lg-1" style="padding-top: 7px;">
												Status
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<select class="form-control" id="status" name="status">
										    		  </select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-1" style="padding-top: 7px; padding-right: 0px;">
												Role Name
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<select class="form-control" id="roleName" name="roleName">
										    		  </select>
												</div>
											</div>
											<div class="col-lg-1" style="padding-top: 7px; padding-right: 0px;">
												Description
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="description"
														name="description" placeholder="">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12" align="right">
												<button type="button" class="btn btn-default" id="btnSearch"
												tabindex="5">
												Search
												</button>
											</div>
										</div>
										<br>
										<div class="box">
										<br>
											<div class="row">
													<div class="col-lg-1" align="right" style="padding-top: 7px;">
														Show
													</div>
													<div class="col-lg-2" align="left">
														<select class="form-control" id="sizeRow" name="sizeRow">
															<option value="10" selected>10</option>
															<option value="25">25</option>
															<option value="50">50</option>
															<option value="100">100</option>
													  	</select>
													</div>
													<div class="col-lg-9" align="right">
														<button type="button" class="btn btn-default" id="btnAdd"
														tabindex="5">
														Add
														</button>
													</div>
											</div>
											<div id="tableArea">
												<div class="clearfix" style="padding-top: 10px;">
													<ul class="pagination pagination-sm no-margin pull-right">
													</ul>
												</div>
												<div class="box-body table-responsive">
												
													<table class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="text-center" style="width: 5%">No.</th>
																<th class="text-center" style="width: 20%">Name</th>
																<th class="text-center" style="width: 20%">Email</th>
																<th class="text-center" style="width: 10%">Role Name</th>
																<th class="text-center" style="width: 10%">NIM / NIP</th>
																<th class="text-center" style="width: 20%">Description</th>
																<th class="text-center" style="width: 10%">Status</th>
																<th class="text-center" style="width: 5%">Action</th>
															</tr>
														</thead>
														<tbody id="tb-body-user">
															<tr>
																<td align="right">
																	1
																</td>
																<td>
																	Asri Maspupah
																</td>
																<td>
																	asri.maspupah89@gmail.com
																</td>
																<td>Member</td>
																<td><p align="center">07501006</p></td>
																<td><p align="center">Prodi Informatika</p></td>
																<td><p align="center">Aktif</p></td>
																<td><button type="button" class="btn btn-default" id="btnEdit"
																tabindex="5">Edit</button></td>
															</tr>
														</tbody>
													</table>
												</div>
												<div class=" clearfix">
													<ul class="pagination pagination-sm no-margin pull-right">
													</ul>
												</div>
											</div>
											<br>
											<!-- /.box-body -->
									</div>
										
									</div>
<!-- 								<div class="modal-footer"> -->
<!-- 									<button type="button" class="btn btn-default" id="btnKembali" -->
<!-- 										tabindex="5"> -->
<!-- 										<i class="fa fa-floppy-o"></i> Pengembalian -->
<!-- 									</button> -->
<!-- 									<button type="button" class="btn btn-default" id="btnClear" -->
<!-- 										tabindex="5"> -->
<!-- 										<i class="fa fa-reply"></i> Clear -->
<!-- 									</button> -->
<!-- 								</div> -->
							
					</form>
					<!-- /.box-body -->
				</div>
			</div>
		</div>
	</div>
</section>



<script type="text/javascript">
	$(document).ready(function() {
		fillComboRole();
		fillComboStatus();
		doSearch(1,$("#sizeRow").val());
	});
		
	var data = null;

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
	        	$('#roleName').empty()
	        	$('#roleName').append('<option value="" selected="selected">-- Select All --</option>');
	        	for(var i=0;  i<data.length; i++){
	        		$('#roleName').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
	        	}
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	           console.log(textStatus, errorThrown);
	        }
	    });
	}
	
	function fillComboStatus(){
		$.ajax({
	        url: "${pageContext.request.contextPath}/api/user/getComboStatus",
	        type: "get",
	        data: {	},
	        contentType:"application/x-www-form-urlencoded",
	        dataType:"json",
	        success: function (response) {
	        	// fill combo Prodi
	        	var data = response.data;
	        	$('#status').empty()
	        	$('#status').append('<option value="" selected="selected">-- Select All --</option>');
	        	for(var i=0;  i<data.length; i++){
	        		$('#status').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
	        	}
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	           console.log(textStatus, errorThrown);
	        }
	    });
	}
	
// 	$("#btnKembali").click(
// 		function() {
// 		alertMessagesModal("MSTD0004ACFM",getMessage(MSTD0004ACFM), "doSubmit()");
// 	});
	$("#btnSearch").click(function() {
		doSearch(1,$("#sizeRow").val())
	});
	
	$("#btnAdd").click(function() {
		window.location="${pageContext.request.contextPath}/userForm";
	});
	
	$("#sizeRow").change(function() {
		doSearch(1,this.value)
	});
		
	function doSearch(page, rowPerPage) {
		$.ajax({
					url : "${pageContext.request.contextPath}/api/user/search",
					type : "post",
					contentType : "application/x-www-form-urlencoded",
					dataType : "json",
					data : {
						page : page,
						rowPerPage : rowPerPage,
						name : $("#name").val(),
						nim_nip : $("#nip").val(),
						status : $("#status").val(),
						role : $("#roleName").val(),
						description : $("#description").val()
					},
					success : function(response) {
						var data = response.data;
						var message = response.message;
						var code = response.message;
						var status = response.status;
						var currentPage = response.currentPage;
						$("#tb-body-user").empty();
						if (data != null && data.length > 0) {
							$("#tableArea").show();
							for (var i = 0; i < data.length; i++) {
								var no = i + 1;
								var icon = "fa fa-lock"
								if (data[i]["status"] == "ST3"){
									 icon = "fa fa-unlock-alt";
								}
								$("#tb-body-user")
										.append(
												"<tr>"
												+ "<td align='center'>"
												+ ((i+1) + ((currentPage-1)*rowPerPage))
												+ "</td>"
												+ "<td>"
												+	data[i]["name"]
												+ "</td>"
												+ "<td>"
												+	data[i]["email"]
												+ "</td>"
												+ "<td>"
												+   data[i]["roleName"]
												+ "</td>"
												+"<td><p align='center'>"
												+   data[i]["nim"]
												+"</p></td>"
												+"<td><p align='center'>"
												+   data[i]["description"]
												+"</p></td>"
												+"<td><p align='center'>"
												+   data[i]["statusName"]
												+"</p></td>"
												+"<td align='center'>"
												+"<a onclick='doEdit(\""+data[i]["email"]+"\")'><i class='fa fa-pencil-square-o'></i></a>"
												+"&nbsp;"
												+"<a onclick='doDelete(\""+data[i]["email"]+"\", \""+data[i]["name"]+"\")'><i class='fa fa-trash'></i></a>"
												+"&nbsp;"
												+"<a onclick='doDeActivate(\""+data[i]["email"]+"\",\""+data[i]["name"]+"\", \""+data[i]["status"]+"\")'><i class='"+icon+"'></i></a>"
												+"</td>"
												+"</tr>"
											);
								}
						
						}else{
							$("#tableArea").hide();
						} 
						showMessage(code, message);
						pagination3(response, $("#sizeRow").val());
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus, errorThrown);
					}
				});
	}
	function doEdit(id){
		window.location="${pageContext.request.contextPath}/userForm?email="+id;
	}
	function doDelete(id, name){
		alertMessagesModal("Confirmation",
				getMessage("Are you sure you want to delete :0 data ?", name), 
				"deleteUser(\""+id+"\")");
	}
	function doDeActivate(id,name,status){
		if (status == "ST3"){
			alertMessagesModal("Confirmation",
					getMessage("Are you sure you want to active the :0 data ?", name), 
					"updateStatus(\""+id+"\", \"ST1\" )");
		}else{
			alertMessagesModal("Confirmation",
					getMessage("Are you sure you want to deactivate the :0 data ?", name), 
					"updateStatus(\""+id+"\", \"ST3\" )");
		}
	}
	function updateStatus(id, newStatus){
		$.ajax({
			url : "${pageContext.request.contextPath}/api/user/updateStatus",
			type : "post",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			data : {
				"email" : id,
				"status" : newStatus
			},
			success : function(response) {
				var message = response.message;
				var code = response.message;
				var status = response.status;
				if(status){
					doSearch(1,$("#sizeRow").val())
					alertMessageDisponse();
				}
				showMessage(code, message);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, errorThrown);
			}
		});
	}
	
	function deleteUser(id){
		$.ajax({
			url : "${pageContext.request.contextPath}/api/user/delete",
			type : "post",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			data : {
				"email" : id
			},
			success : function(response) {
				var message = response.message;
				var code = response.message;
				var status = response.status;
				if(status){
					doSearch(1,$("#sizeRow").val())
					alertMessageDisponse();
				}
				showMessage(code, message);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, errorThrown);
			}
		});
	}
	
	
</script>
