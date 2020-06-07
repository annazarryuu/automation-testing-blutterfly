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

	<div class="box">
		<div class="box-header with-border" align="center">
			<h3 class="box-title">
				Information Detail Book Loan Transaction
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
			 <input	type="hidden" id="penalty" name="penalty" value="${loanData.penalty}">
			 <input	type="hidden" id="loanNo" name="loanNo" value="${loanData.loanNo}" >
			 <input	type="hidden" id="actualReturnDate" name="actualReturnDate" value="${loanData.actualReturnedDate}">
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >Member Information</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					  NIM 
					</div>
					<div class="col-md-8">
					 : ${loanData.nim}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					 Member Name 
					</div>
					<div class="col-md-8">
					 : ${loanData.memberName}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					 Majors
					</div>
					<div class="col-md-8">
					 : ${loanData.majorName}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					 Prodi
					</div>
					<div class="col-md-8">
					 : ${loanData.prodiName}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >&nbsp</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >Book Information</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					  Book Code 
					</div>
					<div class="col-md-8">
					 : ${loanData.bookCd}
					</div>
				</div>
				<div class="row col-md-12">
						<div class="col-md-2">
					  Book title 
					</div>
					<div class="col-md-8">
					 : ${loanData.bookTitle}
					</div>
				</div>
				<div class="row col-md-12">
						<div class="col-md-2">
					  Author 
					</div>
					<div class="col-md-8">
					 : ${loanData.bookAuthor}
					</div>
				</div>
				<div class="row col-md-12">
						<div class="col-md-2">
					 Publisher
					</div>
					<div class="col-md-8">
					 : ${loanData.bookPublisher}
					</div>
				</div>
				<div class="row col-md-12">
						<div class="col-md-2">
					  Publication Year
					</div>
					<div class="col-md-8">
					 : ${loanData.bookPublicationYear}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >&nbsp</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >Loan Information</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					  Loan No 
					</div>
					<div class="col-md-8">
					 : ${loanData.loanNo}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					  Loan Date 
					</div>
					<div class="col-md-8">
					 : ${loanData.loanDate}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					  Return Date 
					</div>
					<div class="col-md-8">
					 : ${loanData.returnedDate}
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					  Actual Return Date 
					</div>
					<div class="col-md-8">
					 : ${loanData.actualReturnedDate}
					</div>
				</div>
				<div class="row col-md-12">
						<div class="col-md-2">
					  Nominal Penalty 
					</div>
					<div class="col-md-8">
					 : Rp.${loanData.penalty},-
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >&nbsp</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					</div>
					<div class="col-md-8">
						<button type="button" class="btn btn-default" id="btnCancel"
							tabindex="18">
							BACK
						</button>
					</div>
					
				</div>
			<!-- </form> -->
		</div>
	</div>
</section>



<script type="text/javascript">
$(document).ready(function() {

});

$("#btnCancel").click(function(){
	window.location="${pageContext.request.contextPath}/loanHistory";	
})

</script>
