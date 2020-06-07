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
									<h4 class="modal-title">MEMBER VERIFICATION</h4>
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
												NIM
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="nip"
														name="nim" placeholder="">
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
											<div id="tableArea">
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
													
											</div>
											
												<div class="clearfix" style="padding-top: 10px;">
													<ul class="pagination pagination-sm no-margin pull-right">
													</ul>
												</div>
												<div class="box-body table-responsive">
												
													<table class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="text-center" style="width: 5%">No.</th>
																<th class="text-center" style="width: 25%">Name</th>
																<th class="text-center" style="width: 25%">Email</th>
																<th class="text-center" style="width: 10%">NIM</th>
																<th class="text-center" style="width: 30%">Description</th>
																<th class="text-center" style="width: 5%">Action</th>
															</tr>
														</thead>
														<tbody id="tb-body-user">
															
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

							
					</form>
					<!-- /.box-body -->
				</div>
			</div>
		</div>
	</div>
</section>



<script type="text/javascript">
	$(document).ready(function() {
		doSearch(1,$("#sizeRow").val());
	});
		
	var data = null;

	$("#btnSearch").click(function() {
		doSearch(1,$("#sizeRow").val())
	});
	
	
	$("#sizeRow").change(function() {
		doSearch(1,this.value)
	});
		
	function doSearch(page, rowPerPage) {
		$.ajax({
					url : "${pageContext.request.contextPath}/api/user/searchRegisteredUser",
					type : "post",
					contentType : "application/x-www-form-urlencoded",
					dataType : "json",
					data : {
						page : page,
						rowPerPage : rowPerPage,
						name : $("#name").val(),
						nim : $("#nim").val(),
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
												+"<td><p align='center'>"
												+   data[i]["nim"]
												+"</p></td>"
												+"<td><p align='center'>"
												+   data[i]["description"]
												+"</p></td>"
												+"<td align='center'>"
												+"<a onclick='doApprove(\""+data[i]["email"]+"\", \""+data[i]["name"]+"\")'><i class='fa fa-check'></i></a>"
												+"&nbsp;"
												+"<a onclick='doReject(\""+data[i]["email"]+"\",\""+data[i]["name"]+"\")'><i class='fa fa-close'></i></a>"
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
	
	function doApprove(id,name,status){
		alertMessagesModal("Confirmation",
				getMessage("Are you sure you want to approve the :0 data ?", name), 
				"approve(\""+id+"\")");
	}

	function doReject(id,name,status){
		alertMessagesModal("Confirmation",
				getMessage("Are you sure you want to reject the :0 data ?", name), 
				"reject(\""+id+"\" )");
	}
	function approve(id){
		$.ajax({
			url : "${pageContext.request.contextPath}/api/user/approve",
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
	
	function reject(id){
		$.ajax({
			url : "${pageContext.request.contextPath}/api/user/reject",
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
