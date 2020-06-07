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
				FORM ${mode} LOAN
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
			 <input	type="hidden" id="nimData" name="nimData">
				<div class="row col-md-12">
					<div class="col-md-2" style="padding-top: 7px;">
					<label for="exampleInputEmail1">NIM <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <select class="form-control" id="nim" name="nim">
									<option value="" selected="selected">-- Select One--</option>
								</select>
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Member Name </label>
					</div>
					<div class="col-md-8">
						<p id="memberName">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Majors</label>
					</div>
					<div class="col-md-8">
						<p id="majors">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Prodi</label>
					</div>
					<div class="col-md-8">
						<p id="prodi">[Label]</p>
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
					<label for="exampleInputEmail1" style="padding-top: 7px;">Book Code <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <select class="form-control" id="bookCode" name="bookCode">
									<option value="" selected="selected">-- Select One--</option>
								</select>
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Book Title </label>
					</div>
					<div class="col-md-8">
						<p id="bookTitle">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Author</label>
					</div>
					<div class="col-md-8">
						<p id="author">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Publisher</label>
					</div>
					<div class="col-md-8">
						<p id="publisher">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Publication Year</label>
					</div>
					<div class="col-md-8">
						<p id="publicationYear">[Label]</p>
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
					<label for="exampleInputEmail1">Loan Date</label>
					</div>
					<div class="col-md-8">
						<p id="loanDate">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Return Date</label>
					</div>
					<div class="col-md-8">
						<p id="returnDate">[Label]</p>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1" >&nbsp</label>
					</div>
					<div class="col-md-8">
					</div>
				</div>
				<div class="row col-md-12" id="checkLoanDate">
					<div class="col-md-12">
					 <input	type="checkbox" id="checkUpdateLoanDate" name="checkUpdateLoanDate">
					<label for="exampleInputEmail1" >change the loan date to the current date(Current Date) and change return date too (next 7 day from Current Date)</label>
					</div>
				</div>
				
				<div class="row col-md-12">
					<div class="col-md-2">
					</div>
					<div class="col-md-8">
						<button type="button" class="btn btn-default" id="btnSubmit"
							tabindex="18">
							SUBMIT
						</button>
						<button type="button" class="btn btn-default" id="btnReset"
							tabindex="18">
							RESET
						</button>
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



<script type="text/javascript">
$(document).ready(function() {
	fillComboNIM();
	fillComboBook();
	
	
	setTimeout(function(){ initialData(); }, 1000);	
});


$("#btnSubmit").click(function(){
	if(doValidation()){
		doSave();
	}
})

function getCurrentDate(){
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = day + '/' +
	    (month<10 ? '0' : '') + month + '/' +
	    (day<10 ? '0' : '') + d.getFullYear();
	return output;
} 
$("#nim").change(function(){
	getDataNIM(this.value);
});
$("#checkUpdateLoanDate").change(function(){
	if($(this).prop("checked") == true){
		$("#loanDate").html(getCurrentDate());
		$("#returnDate").html(get7DayDate());
	}else{
		$("#loanDate").html("${loanData.loanDate}");
		$("#returnDate").html("${loanData.returnedDate}");
	}
});
$("#bookCode").change(function(){
	getDataBook(this.value);
});
function getDataNIM(email){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/user/getUserByEmail",
        type: "post",
        data: {email : email },
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill data Book
        	if (response.status){
	        	var data = response.data[0];
	        	$("#nimData").val(data.nim);
	        	$("#memberName").html(data.name);
	        	$("#majors").html(data.jurusan);
	        	$("#prodi").html(data.programStudi);
        	}else{
        		$("#nimData").val("");
        		$("#memberName").html("[Label]");
	        	$("#majors").html("[Label]");
	        	$("#prodi").html("[Label]");
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}
function getDataBook(bookCode){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/book/getBookByCode",
        type: "post",
        data: {bookCode : bookCode },
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	
        	// fill data Book
        	if (response.status){
	        	var data = response.data[0];
	        	$("#bookTitle").html(data.title);
	        	$("#author").html(data.author);
	        	$("#publisher").html(data.publisher);
	        	$("#publicationYear").html(data.publishYear);
        	}else{
        		$("#bookTitle").html("[Label]");
	        	$("#author").html("[Label]");
	        	$("#publisher").html("[Label]");
	        	$("#publicationYear").html("[Label]");
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}
function get7DayDate(){
	var d = new Date();
	d.setDate(d.getDate() + 7);

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = day + '/' +
	    (month<10 ? '0' : '') + month + '/' +
	    (day<10 ? '0' : '') + d.getFullYear();
	return output;
} 
function initialData(){
	$("#nim").val("${loanData.email}");
	$("#bookCode").val("${loanData.bookCd}");
	if("${mode}" == "ADD"){
		$("#loanDate").html(getCurrentDate());
		$("#returnDate").html(get7DayDate());
		$("#checkLoanDate").hide();
	}else{
		$("#loanDate").html("${loanData.loanDate}");
		$("#returnDate").html("${loanData.returnedDate}");
		$("#checkLoanDate").show();
		$("#checkUpdateLoanDate"). prop("checked", false);
	}
	getDataNIM($("#nim").val());
	getDataBook($("#bookCode").val());
}
function doSave(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/pinjam/save",
        type: "post",
        data: {	loanNo:"${loanData.loanNo}",
    			bookCd:$("#bookCode").val(),
        		nim:$("#nimData").val(),
        		loanDate:$("#loanDate").html(),
        		returnDate:$("#returnDate").html(),
        		mode: "${mode}" },
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	if (response.status){
        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/pinjam"; }, 1000);	
        	}
        	showMessage(response.code, response.message);
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}
$("#btnReset").click(function(){
	initialData()
// 	$("#name").val("");
// 	$("#email").val("");
// 	$("#roleId").val("");
// 	$("#nip").val("");
// 	$("#divId").val("");
// 	$("#nim").val("");
// 	$("#majorsId").val("");
// 	$("#prodiId").val("");
// 	$("#formMember").hide();
// 	$("#formOfficial").hide();
})
$("#btnCancel").click(function(){
	window.location="${pageContext.request.contextPath}/pinjam";	
})

function doValidation(){
	var err = 0;
	
	if ($("#nim").val() == ""){
		showMessage("MSTD0031AERR","Field NIM should be filled in");
		err++;
	}
	if ($("#bookCode").val() == ""){
		showMessage("MSTD0031AERR","Field Book Code should be filled in");
		err++;
	}

	if(err == 0){
		return true;
	}else{
		return false;
	}
	
}
function fillComboNIM(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/pinjam/getComboNIM",
        type: "get",
        data: {	},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Prodi
        	var data = response.data;
        	$('#nim').empty()
        	$('#nim').append('<option value="" selected="selected">-- Select One --</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#nim').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}	

function fillComboBook(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/pinjam/getComboBook",
        type: "get",
        data: {	},
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	// fill combo Prodi
        	var data = response.data;
        	$('#bookCode').empty()
        	$('#bookCode').append('<option value="" selected="selected">-- Select One --</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#bookCode').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}	
</script>
