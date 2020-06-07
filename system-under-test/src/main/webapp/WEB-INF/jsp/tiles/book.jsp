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
									<h4 class="modal-title">Book Data Management</h4>
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
												Publisher
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="publisher"
														name="publisher" placeholder="">
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
											<div class="col-lg-1" style="padding-top: 7px;padding-right: 0px;">
												Publish Year
											</div>
											<div class="col-lg-3">
												<div class="form-group has-feedback">
													<input type="text"  class="form-control" id="pubYear"
														name="pubYear" placeholder="">
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
																<th class="text-center" valign="middle" style="width: 10%">Book Code</th>
																<th class="text-center" valign="middle" style="width: 15%">Title</th>
																<th class="text-center" valign="middle" style="width: 10%">Author</th>
																<th class="text-center" valign="middle" style="width: 10%">Publisher</th>
																<th class="text-center" valign="middle" style="width: 15%">Publication Year</th>
																<th class="text-center" valign="middle" style="width: 10%">Category</th>
																<th class="text-center" valign="middle" style="width: 5%">Stock</th>
																<th class="text-center" valign="middle" style="width: 5%">Available</th>
																<th class="text-center" valign="middle" style="width: 5%">Location</th>
																<th class="text-center" valign="middle" style="width: 10%">Action</th>
															</tr>
														</thead>
														<tbody id="tb-body-book">
															<tr valign="middle">
																<td>
																	<input type="checkbox"  id="checkData"
																	id="checkData">
																</td>
																<td>
																B001
																</td>
																<td>
																Harry Potter : The chamber of secret
																</td>
																<td>
																J.K. Rowling
																</td>
																<td>
																Gramedia
																</td>
																<td>
																1990
																</td>
																<td>
																Science Fiction, Drama
																</td>
																<td>
																10
																</td>
																<td>
																10
																</td>
																<td><p align="center">A - 1</p></td>
																<td><p align="center"><button type="button" class="btn btn-default" id="btnDetail"
																tabindex="5">Detail</button>
																<button type="button" class="btn btn-default" id="btnEdit"
																tabindex="5">Edit</button></td>
															</tr>
															<tr>
																<td colspan="4"><p align="center">Data Not Found</p></td>
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

$("#btnAdd").click(function() {
	window.location="${pageContext.request.contextPath}/bookForm";
});

$("#sizeRow").change(function() {
	doSearch(1,this.value)
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
					publisher : $("#publisher").val(),
					publishYear : $("#pubYear").val(),
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
											+	data[i]["bookCode"]
											+ "</td>"
											+ "<td>"
											+	data[i]["title"]
											+ "</td>"
											+ "<td>"
											+   data[i]["author"]
											+ "</td>"
											+"<td><p align='left'>"
											+   data[i]["publisher"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["publishYear"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["categoryName"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["stock"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["avaliable"]
											+"</p></td>"
											+"<td><p align='center'>"
											+   data[i]["location"]
											+"</p></td>"
											+"<td align='center'>"
											+"<a onclick='doDetail(\""+data[i]["bookCode"]+"\",\""+data[i]["title"]+"\")'><i class='fa fa-info-circle'></i></a>"
											+"&nbsp;"
											+"<a onclick='doEdit(\""+data[i]["bookCode"]+"\")'><i class='fa fa-pencil-square-o'></i></a>"
											+"&nbsp;"
											+"<a onclick='doDelete(\""+data[i]["bookCode"]+"\", \""+data[i]["title"]+"\")'><i class='fa fa-trash'></i></a>"
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
	window.location="${pageContext.request.contextPath}/bookForm?bookCd="+id;
}
function doDetail(id){
	window.location="${pageContext.request.contextPath}/bookDetail?bookCd="+id;
}
function doDelete(id, name){
	alertMessagesModal("Confirmation",
			getMessage("Are you sure you want to delete :0 data ?", name), 
			"deleteBook(\""+id+"\")");
}

function deleteBook(id){
	$.ajax({
		url : "${pageContext.request.contextPath}/api/book/delete",
		type : "post",
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		data : {
			"bookCd" : id
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
