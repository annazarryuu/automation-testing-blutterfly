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
									<h4 class="modal-title">Book Loan Transaction</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-lg-2" style="padding-top: 7px;">
												Member Name
											</div>
											<div class="col-lg-4">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="memberName"
														name="memberName" >
												</div>
											</div>
											<div class="col-lg-2" style="padding-top: 7px;">
												Loan Date From
											</div>
											<div class="col-lg-4">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="loanDateFrom"
														name="loanDateFrom" placeholder="">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" style="padding-top: 7px;">
												Majors
											</div>
											<div class="col-lg-4">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="majors"
														name="majors" >
												</div>
											</div>
											<div class="col-lg-2" style="padding-top: 7px;padding-right: 0px;">
												Loan Date To
											</div>
											<div class="col-lg-4">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="loanDateTo"
														name="loanDateTo">
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
																<th class="text-center" valign="middle" style="width: 10%">Loan No</th>
																<th class="text-center" valign="middle" style="width: 10%">NIM</th>
																<th class="text-center" valign="middle" style="width: 15%">Member Name</th>
																<th class="text-center" valign="middle" style="width: 20%">Majors</th>
																<th class="text-center" valign="middle" style="width: 15%">Book</th>
																<th class="text-center" valign="middle" style="width: 10%">Loan Date</th>
																<th class="text-center" valign="middle" style="width: 10%">Return Date</th>
																<th class="text-center" valign="middle" style="width: 5%">Action</th>
															</tr>
														</thead>
														<tbody id="tb-body-book">
															
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
</section>



<script type="text/javascript">
$(document).ready(function() {
	$("#loanDateFrom").datepicker({
	    format: 'dd/mm/yyyy'
	});
	$("#loanDateTo").datepicker({
	    format: 'dd/mm/yyyy'
	});
	
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
				url : "${pageContext.request.contextPath}/api/pinjam/search",
				type : "post",
				contentType : "application/x-www-form-urlencoded",
				dataType : "json",
				data : {
					page : page,
					rowPerPage : rowPerPage,
					memberName : $("#memberName").val(),
					majors : $("#majors").val(),
					loanDateFrom : $("#loanDateFrom").val(),
					loanDateTo : $("#loanDateTo").val(),
					status : "BL1"
				},
				success : function(response) {
					var data = response.data;
					var message = response.message;
					var code = response.message;
					var status = response.status;
					var currentPage = response.currentPage;
					$("#tb-body-book").empty();
					if (data != null && data.length > 0) {
						$("#tableArea").show();
						for (var i = 0; i < data.length; i++) {
							var no = i + 1;
							var icon = "fa fa-lock"
							if (data[i]["status"] == "ST3"){
								 icon = "fa fa-unlock-alt";
							}
							$("#tb-body-book")
									.append(
											"<tr>"
											+ "<td align='center'>"
											+ ((i+1) + ((currentPage-1)*rowPerPage))
											+ "</td>"
											+ "<td>"
											+	data[i]["loanNo"]
											+ "</td>"
											+ "<td>"
											+	data[i]["nim"]
											+ "</td>"
											+ "<td>"
											+   data[i]["memberName"]
											+ "</td>"
											+"<td><p align='left'>"
											+   data[i]["majors"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["bookCd"] + " " + data[i]["bookTitle"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["loanDate"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["returnedDate"]
											+"</p></td>"
											+"<td align='center'>"
											+"<a onclick='doKembali(\""+data[i]["loanNo"]+"\")'><i class='fa fa-book'></i></a>"
											+"&nbsp;"
											+"<a onclick='doDetail(\""+data[i]["loanNo"]+"\")'><i class='fa fa-info-circle'></i></a>"
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
function doKembali(id){
	window.location="${pageContext.request.contextPath}/kembaliForm?loanNo="+id;
}

function doDetail(id){
	window.location="${pageContext.request.contextPath}/kembaliInfo?loanNo="+id;
}
	
</script>
