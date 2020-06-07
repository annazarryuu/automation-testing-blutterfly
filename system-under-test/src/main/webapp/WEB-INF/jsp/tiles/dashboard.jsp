<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
   String name = request.getSession().getAttribute("name").toString();
   String role = request.getSession().getAttribute("role").toString();
   String rolename = request.getSession().getAttribute("roleName").toString();
%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<form id="form-id" onsubmit="return false">
					<div class="modal-header" align="center">
						<h4 class="modal-title">WELCOME <%=name%> as <%=rolename%> POLBAN LIBRARY</h4>
					</div>
				</form>
					<!-- /.box-body -->
			</div>
		</div>
	</div>
</section>
