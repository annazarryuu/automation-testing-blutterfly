<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- <section class="content-header"> -->
<!-- 	<h1>Change Password</h1> -->
<!-- 	<ol class="breadcrumb"> -->
<!-- 		<li><a href="javascript:void(0);"><i class="fa fa-dashboard"></i> -->
<!-- 				Beranda</a></li> -->
<!-- 	</ol> -->
<!-- </section> -->
<section class="content">

	<div class="box">
		<div class="box-header with-border" align="center">
			<h3 class="box-title">
				FORM ${mode} MISSING or DAMAGE BOOK
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
				<div class="row col-md-9">
					<div class="col-md-3">
					<label for="exampleInputEmail1">Date</label>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							 <input
								type="text" class="form-control" id="dateData"
								name="dateData" placeholder="Date" tabindex="1"
								maxlength="50" required="required">
						</div>
					</div>
				</div>
				<div class="row col-md-9">
					<div class="col-md-3">
					<label for="exampleInputEmail1">Number of Book</label>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							 <input
								type="number" class="form-control" id="numberOfBook"
								name="numberOfBook" placeholder="Number of Book" tabindex="2"
								maxlength="50" required="required">
						</div>
					</div>
				</div>
				<div class="row col-md-9">
					<div class="col-md-3">
					<label for="exampleInputEmail1">Reason</label>
					</div>
					<div class="col-md-6">
						<div class="form-group">
						<textarea id="reason" name="reason" class="form-control" rows="6" required="required" >
						</textarea>
						</div>
					</div>
				</div>
				<div class="row col-md-9">
					<div class="col-md-3">
					</div>
					<div class="col-md-2">
						<button type="button" class="btn btn-default" id="btnSubmit"
							tabindex="18">
							SUBMIT
						</button>
					</div>
					<div class="col-md-2">
						<button type="button" class="btn btn-default" id="btnReset"
							tabindex="18">
							RESET
						</button>
					</div>
					<div class="col-md-2">
						<button type="button" class="btn btn-default" id="btnCancel"
							tabindex="18">
							CANCEL
						</button>
					</div>
					
				</div>
			<!-- </form> -->
		</div>
	</div>
</section>
	
<script
	src="${pageContext.request.contextPath}/assets/d3Js/d3.v3.min.js"></script>
	
<script type="text/javascript">
	var bookCd = "${bookData.bookCd}";
	$( document ).ready(function() {
		$("#dateData").datepicker({
		    format: 'dd/mm/yyyy'
		});
		
		initialData();
	});

	$("#btnSubmit").click(function(){
		if(doValidation()){
			doSave();
		}
	});
	
	function doValidation(){
		var err = 0;
		
		if ($("#date").val() == ""){
			showMessage("MSTD0031AERR","Field Date should be filled in");
			err++;
		}
		if ($("#numberOfBook").val() == ""){
			showMessage("MSTD0031AERR","Field Number Of Book should be filled in");
			err++;
		}
				
		if(err == 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	function doSave(){
		$.ajax({
	        url: "${pageContext.request.contextPath}/api/bookMissing/save",
	        type: "post",
	        data: {	bookCd: bookCd,
	        		date:$("#dateData").val(),
	        		reason:$("#reason").val(),
	        		numberOfBook:$("#numberOfBook").val(),
	        		mode: "${mode}" },
	        contentType:"application/x-www-form-urlencoded",
	        dataType:"json",
	        success: function (response) {
	        	if (response.status){
	        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/bookDetail?bookCd="+bookCd; }, 1000);	
	        	}
	        	showMessage(response.code, response.message);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	           console.log(textStatus, errorThrown);
	        }
	    });
	}
	
	$("#btnCancel").click(function(){
		window.location.replace('${pageContext.request.contextPath}/bookDetail?bookCd='+bookCd);
	});
	$("#btnReset").click(function(){
		initialData();
	});
	
	function initialData(){
		$("#dateData").val("${bookData.date}");
		$("#reason").val("${bookData.reason}");
		$("#numberOfBook").val("${bookData.numberOfBook}");
		
	}
</script>