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
									<h4 class="modal-title">Book Catalog</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-lg-1" style="padding-top: 7px;">
												Title
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="title"
														name="title" placeholder="Title">
												</div>
											</div>
											<div class="col-lg-1" style="padding-top: 7px;">
												Subject
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="subject"
														name="subject" placeholder="Subject">
												</div>
											</div>
											<div class="col-lg-1" style="padding-top: 7px;">
												Category
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<select class="form-control" id="category" name="category">
										    		  </select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-1" style="padding-top: 7px;">
												Author
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="author"
														name="author" placeholder="Author">
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
												<div class="box-body table-responsive">
												
													<table class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="text-center" style="width: 5%">No</th>
																<th class="text-center" style="width: 66%">Title</th>
																<th class="text-center" style="width: 15%">Location</th>
																<th class="text-center" style="width: 5%">Action</th>
															</tr>
														</thead>
														<tbody id="tb-body-book">
														</tbody>
													</table>
												</div>
												<div class=" clearfix">
													<ul class="pagination pagination-sm no-margin pull-right">
													<li><a>First</a></li>
													<li><a> << </a></li>
													<li><a> >> </a></li>
													<li><a> Last </a></li>
													
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
		fillComboCategoryBook();
		
		doSearch(1,$("#sizeRow").val());
	});
	var data = null;

	function fillComboCategoryBook(){
		$.ajax({
	        url: "${pageContext.request.contextPath}/api/book/getComboCategory",
	        type: "get",
	        data: {	},
	        contentType:"application/x-www-form-urlencoded",
	        dataType:"json",
	        success: function (response) {
	        	// fill combo Prodi
	        	var data = response.data;
	        	$('#category').empty()
	        	$('#category').append('<option value="" selected="selected">-- Select All --</option>');
	        	for(var i=0;  i<data.length; i++){
	        		$('#category').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
	        	}
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	           console.log(textStatus, errorThrown);
	        }
	    });
	}
	
	$("#btnSearch").click(function() {
		doSearch(1,$("#sizeRow").val())
	});
	
	function doSearch(page, rowPerPage) {
		$.ajax({
					url : "${pageContext.request.contextPath}/api/book/search",
					type : "post",
					contentType : "application/x-www-form-urlencoded",
					dataType : "json",
					data : {
						page : page,
						rowPerPage : rowPerPage,
						title : $("#title").val(),
						author : $("#author").val(),
						subject : $("#subject").val(),
						category : $("#category").val()
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
												+ 	"<div class='col-lg-2' style='padding-top: 7px;'>"
												+		"<img src='" + data[i]["bookCover"] + "' height='100px' width='100px'>"
												+ 	"</div>"
												+ 	"<div class='col-lg-10' style='padding-top: 7px;'>"
												+ 		"<a>" + data[i]["title"] + "</a>"
												+		"<br>"
												+		"<p align='justify'>"
												+			data[i]["bookSummary"] 
												+		"</p>"
												+		"<b>Category : </b> " + data[i]["categoryName"] 
												+		" <b>Author : </b> " + data[i]["author"]
												+		" <b>Publisher : </b> " +  data[i]["publisher"]
												+		" <b>Available : </b> " +   data[i]["avaliable"]
												+	"</div>"
												+ "</td>"
		
												
												+"<td><p align='center'>"
												+   data[i]["location"]
												+"</p></td>"
												+"<td align='center'>"
												+"<a onclick='doDetail(\""+data[i]["bookCode"]+"\")'><i class='fa fa-info-circle'></i></a>"
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
	
	function doDetail(id){
		window.location="${pageContext.request.contextPath}/katalogDetail?bookCd="+id;
	}
</script>
