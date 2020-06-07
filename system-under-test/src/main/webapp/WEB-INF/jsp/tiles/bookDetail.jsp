<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<form id="form-id" onsubmit="return false">
						<input type="hidden" id="stFormAction" name="stFormAction">
						<input type="hidden" id="userId" name="userId">
						
								<div class="modal-header" align="center">
									<h4 class="modal-title">Book Data Detail</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-lg-2" align="center" style="padding-top: 7px;">
												<img src="${bookData.bookCover}" height="150px" width="150px">
											</div>
											<div class="col-lg-10">
													${bookData.title}
													<br>
													${bookData.bookSummary}
											</div>
										</div>
										
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Category
											</div>
											<div class="col-lg-3">
											 : ${bookData.categoryName}
											</div>
											<div class="col-lg-2">
												Publisher
											</div>
											<div class="col-lg-4">
											 :  ${bookData.publisher}
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Subject 1
											</div>
											<div class="col-lg-3">
											 :  ${bookData.subject1}
											</div>
											<div class="col-lg-2">
												Publication Year
											</div>
											<div class="col-lg-4">
											 :  ${bookData.publishYear}
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Subject 2
											</div>
											<div class="col-lg-3">
											 :  ${bookData.subject2}
											</div>
											<div class="col-lg-2">
												Place Of Publication
											</div>
											<div class="col-lg-4">
											 :  ${bookData.placePublication}
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Author
											</div>
											<div class="col-lg-3">
											 :  ${bookData.author}
											</div>
											<div class="col-lg-2">
												Number Of Page
											</div>
											<div class="col-lg-4">
											 :  ${bookData.numberPage}
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Location
											</div>
											<div class="col-lg-3">
											 :  ${bookData.location}
											</div>
											<div class="col-lg-2">
												Borrowed
											</div>
											<div class="col-lg-4">
											 :  ${bookData.borrowed}
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Stock
											</div>
											<div class="col-lg-3">
											 :  ${bookData.stock}
											</div>
											<div class="col-lg-2">
												Lost / Broken
											</div>
											<div class="col-lg-4">
											 :  ${bookData.lost}
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												Avaliable
											</div>
											<div class="col-lg-3">
											 :  ${bookData.avaliable}
											</div>
											
										</div>
										<br>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-8">
												<div class="col-lg-6" >
													<b>List of missing or damage book data</b>
												</div>
												<div class="col-lg-6" align="right">
													<button type="button" class="btn btn-default" id="btnAddMissing"
																tabindex="5">Add</button>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-8">
												<div id="tableMissingBookArea">
													<div class="box-body table-responsive">
														<table class="table table-bordered table-hover">
															<thead>
																<tr>
																	<th class="text-center" style="width: 4%">N0</th>
																	<th class="text-center" valign="middle" style="width: 5%">Date</th>
																	<th class="text-center" valign="middle" style="width: 25%">Number of Book</th>
																	<th class="text-center" valign="middle" style="width: 61%">Reason</th>
																	<th class="text-center" valign="middle" style="width: 5%">Action</th>
																</tr>
															</thead>
															<tbody id="tb-body-missingBook">
																<tr valign="middle">
																	<td>
																		1
																	</td>
																	<td align="center">
																		05/03/2020
																	</td>
																	<td align="right">
																		5
																	</td>
																	<td>
																		Hilang, lupa nyimpen
																	</td>
																	<td>
																	<button type="button" class="btn btn-default" id="btnEdit"
																	tabindex="5">Edit</button></td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
										
					</form>
					<!-- /.box-body -->
				</div>
			</div>
		</div>
</section>



<script type="text/javascript">
    var bookCd = "${bookData.bookCode}";
    $(document).ready(function() {
    	doSearchMissingBook();
    });
	$("#btnUploadProfilePicture").click(function() {
		window.location="${pageContext.request.contextPath}/uploadProfilePicture";		
	});
	
	$("#btnChangePassword").click(function() {
		window.location="${pageContext.request.contextPath}/changePassword";
	});
	
	$("#btnAddMissing").click(function() {
		window.location="${pageContext.request.contextPath}/bookFormMissing?bookCd="+bookCd;
	});
	
	function doSearchMissingBook() {
		$.ajax({
					url : "${pageContext.request.contextPath}/api/bookMissing/search",
					type : "post",
					contentType : "application/x-www-form-urlencoded",
					dataType : "json",
					data : {
						bookCd : bookCd
					},
					success : function(response) {
						var data = response.data;
						var message = response.message;
						var code = response.message;
						var status = response.status;
						$("#tb-body-missingBook").empty();
						if (data != null && data.length > 0) {
							$("#tableMissingBookArea").show();
							for (var i = 0; i < data.length; i++) {
								var no = i + 1;
								
								$("#tb-body-missingBook")
										.append(
												"<tr>"
												+ "<td align='center'>"
												+ ((i+1))
												+ "</td>"
												+ "<td>"
												+	data[i]["date"]
												+ "</td>"
												+ "<td>"
												+	data[i]["numberOfBook"]
												+ "</td>"
												+ "<td>"
												+   data[i]["reason"]
												+ "</td>"
												+"<td align='center'>"
												+"<a onclick='doEdit(\""+data[i]["bookCd"]+"\", \""+data[i]["date"]+"\")'><i class='fa fa-pencil-square-o'></i></a>"
												+"&nbsp;"
												+"<a onclick='doDelete(\""+data[i]["bookCd"]+"\", \""+data[i]["date"]+"\")'><i class='fa fa-trash'></i></a>"
												+"</td>"
												+"</tr>"
											);
								}
						
						}else{
							$("#tableMissingBookArea").hide();
						} 
						showMessage(code, message);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus, errorThrown);
					}
				});
	}

	function doEdit(bookCd, date){
		window.location="${pageContext.request.contextPath}/bookFormMissing?bookCd="+bookCd+"&date="+date;
	}
	
	function doDelete(bookCd, date){
		alertMessagesModal("Confirmation",
				getMessage("Are you sure you want to delete :0 data ?", date), 
				"deleteMissingBook(\""+bookCd+"\",\""+date+"\")");
	}
	function deleteMissingBook(bookCd, date){
		$.ajax({
			url : "${pageContext.request.contextPath}/api/missingBook/delete",
			type : "post",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			data : {
				"bookCd" : bookCd,
				"date" : date
			},
			success : function(response) {
				var message = response.message;
				var code = response.message;
				var status = response.status;
				if(status){
					setTimeout(function(){window.location="${pageContext.request.contextPath}/bookDetail?bookCd="+bookCd; }, 1000);
					
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