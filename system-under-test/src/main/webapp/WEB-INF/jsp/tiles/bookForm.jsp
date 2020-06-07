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
				FORM ${mode} BOOK
			</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive">
			<!-- <form method="post"> -->
			 <input	type="hidden" id="bookCd" name="bookCd">
			 <input	type="hidden" id="bookCoverBase64" name="bookCoverBase64">
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Title <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <input
								type="text" class="form-control" id="title"
								name="title" placeholder="" tabindex="12"
								maxlength="50">
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-2">
					<label for="exampleInputEmail1">Book Summary <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							 <textarea
								class="form-control" id="bookSummary"
								name="bookSummary" placeholder="" tabindex="12"
								maxlength="50">
							</textarea>
								
						</div>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-3">
					<label for="exampleInputEmail1">Upload a book cover photo <span style="color:red;">*</span></label>
					</div>
					<div class="col-md-7">
						<div class="form-group">
						    <img src="" id="coverPreview" height="100px" width="100px"/>
							<input
								type="file" class="form-control" id="coverBook"
								name="coverBook" placeholder="" accept="image/*" tabindex="12"
								maxlength="50" style="margin-top: 10px;">
						</div>
					</div>
				</div>
					<div class="row col-md-12">
							<div class="col-md-2">
							<label for="exampleInputEmail1">Category <span style="color:red;">*</span></label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<select class="form-control" id="category" name="category">
									<option value="" selected="selected">-- Select One--</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Subject 1</label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
								type="text" class="form-control" id="subject1"
								name="subject1" placeholder="" tabindex="12"
								maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Subject 2</label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
								type="text" class="form-control" id="subject2"
								name="subject2" placeholder="" tabindex="12"
								maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Author</label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="text" class="form-control" id="author"
									name="author" placeholder="" tabindex="12"
									maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Publisher </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="text" class="form-control" id="publisher"
									name="publisher" placeholder="" tabindex="12"
									maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Publication year </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="number" class="form-control" id="publicationYear"
									name="publicationYear" placeholder="" tabindex="12"
									maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Place of Publication </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="text" class="form-control" id="placePublication"
									name="placePublication" placeholder="" tabindex="12"
									maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Number of Page</label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="number" class="form-control" id="numberOfPage"
									name="numberOfPage" placeholder="" tabindex="12"
									maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Location </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="text" class="form-control" id="location"
									name="location" placeholder="" tabindex="12"
									maxlength="50">
							</div>
						</div>
					</div>
					<div class="row col-md-12">
						<div class="col-md-2">
						<label for="exampleInputEmail1">Stock <span style="color:red;">*</span> </label>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input
									type="number" class="form-control" id="stock"
									name="stock" placeholder="" tabindex="12"
									maxlength="50">
							</div>
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
	fillComboCategoryBook();
	
	setTimeout(function(){ initialData(); }, 1000);	
});

$("#coverBook").change(function(){
	  var file = $(this).val();
	  var ext = file.split('.').pop();
	  setImage(this)
});

function setImage(input) {
  if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
          $('#bookCoverBase64').val(e.target.result);
          $('#coverPreview').attr('src', e.target.result);
      }
      reader.readAsDataURL(input.files[0]);
  }
}

$("#btnSubmit").click(function(){
	if(doValidation()){
		doSave();
	}
})

function initialData(){
	$("#bookCd").val("${bookData.bookCode}");
	$("#title").val("${bookData.title}");
	$("#bookSummary").val("${bookData.bookSummary}");
	$("#bookCoverBase64").val("${bookData.bookCover}");
	if ($("#bookCoverBase64").val() != ""){
		 $('#coverPreview').attr('src', $("#bookCoverBase64").val());
	}else{
		$("#coverBook").val("");
		$('#coverPreview').attr('src',"data:image/jpeg;base64,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AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//Z");
	}
	$("#category").val("${bookData.category}");
	$("#subject1").val("${bookData.subject1}");
	$("#subject2").val("${bookData.subject2}");
	$("#author").val("${bookData.author}");
	$("#publisher").val("${bookData.publisher}");
	$("#publicationYear").val("${bookData.publishYear}");
	$("#placePublication").val("${bookData.placePublication}");
	$("#numberOfPage").val("${bookData.numberPage}");
	$("#location").val("${bookData.location}");
	$("#stock").val("${bookData.stock}");
		
}
function doSave(){
	$.ajax({
        url: "${pageContext.request.contextPath}/api/book/save",
        type: "post",
        data: {	bookCd:$("#bookCd").val(),
        		title:$("#title").val(),
        		bookCover:$("#bookCoverBase64").val(),
        		bookSummary:$("#bookSummary").val(),
        		category:$("#category").val(),
        		subject1:$("#subject1").val(),
        		subject2:$("#subject2").val(),
        		author:$("#author").val(),
        		publisher:$("#publisher").val(),
        		publicationYear:$("#publicationYear").val(),
        		placePublication:$("#placePublication").val(),
        		numberOfPage:$("#numberOfPage").val(),
        		location:$("#location").val(),
        		stock:$("#stock").val(),
        		mode: "${mode}" },
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success: function (response) {
        	if (response.status){
        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/book"; }, 1000);	
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
	window.location="${pageContext.request.contextPath}/book";	
})

function doValidation(){
	var err = 0;
	
	if ($("#title").val() == ""){
		showMessage("MSTD0031AERR","Field Title should be filled in");
		err++;
	}
	if ($("#bookCover").val() == ""){
		showMessage("MSTD0031AERR","Field Book Cover Photo should be filled in");
		err++;
	}
	if ($("#bookSummary").val() == ""){
		showMessage("MSTD0031AERR","Field Book Summary should be filled in");
		err++;
	}
	if ($("#category").val() == ""){
		showMessage("MSTD0031AERR","Field Category should be filled in");
		err++;
	}
	if ($("#stock").val() == ""){
		showMessage("MSTD0031AERR","Field Stock should be filled in");
		err++;
	}
	

	if(err == 0){
		return true;
	}else{
		return false;
	}
	
}
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
        	$('#category').append('<option value="" selected="selected">-- Select One --</option>');
        	for(var i=0;  i<data.length; i++){
        		$('#category').append('<option value="'+data[i].value+'" >'+data[i].label+'</option>');
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }
    });
}	
</script>
