<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.id.perpus.user.UserModel" %>
<% UserModel dto = (UserModel) request.getSession().getAttribute("userModel");%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<form id="form-id" onsubmit="return false">
						<input type="hidden" id="stFormAction" name="stFormAction">
						<input type="hidden" id="userId" name="userId">
						
								<div class="modal-header" >
									<h4 class="modal-title">UPLOAD PHOTO</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-lg-2" align="center" style="padding-top: 7px;">
												<img src="<%= dto.getProfilePicture() %>" id="pp" height="100px" width="100px">
											</div>
											<div class="col-lg-3">
												<input type="file"  class="form-control" id="filePicture"
													name="filePicture" placeholder="" accept="image/*">
											</div>
										</div>
										
										<div class="row">
											<div class="col-lg-2" >
											</div>
											<div class="col-lg-1">
												<button type="button" class="btn btn-default" id="btnSubmit"
												tabindex="5">
												SUBMIT
												</button>
											</div>
											<div class="col-lg-1">
												<button type="button" class="btn btn-default" id="btnCancel"
												tabindex="5">
												CANCEL
												</button>
											</div>
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
	$("#btnSubmit").click(function() {
		doSubmit();
	});
	
	 $("#filePicture").change(function(){
		  var file = $(this).val();
		  var ext = file.split('.').pop();
		  previewImage(this)
	 });
	 
	 function previewImage(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#pp').attr('src', e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	function doSubmit() {
		$.ajax({
			url : "${pageContext.request.contextPath}/api/user/changeProfilePicture",
			type : "post",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			data : {
				"email" : "<%= dto.getEmail() %>",
				"profile_picture" : $('#pp').attr('src')
			},
			success : function(response) {
				if (response.status){
	        		setTimeout(function(){ window.location="${pageContext.request.contextPath}/profile"; }, 1000);	
	        	}
	        	showMessage(response.code, response.message);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus, errorThrown);
			}
		});
	}
	
	$("#btnCancel").click(function() {
		window.location="${pageContext.request.contextPath}/profile";
	});	
</script>
