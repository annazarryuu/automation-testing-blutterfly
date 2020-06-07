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
    	
    });
	
</script>