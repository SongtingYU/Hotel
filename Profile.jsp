<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.comp9321.assign2.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="HeaderFile.jsp"%>

<title>My Profile</title>
</head>
<body>
	<%
		User_Bean update_user = (User_Bean) request.getSession().getAttribute("update_user");
	%>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 personal-info">
				<h3>Personal info</h3>
				<form class="form-horizontal" action="control" method="POST">
					<div class="form-group">
						<label class="col-lg-3 control-label">Username:</label> <label
							class="col-lg-8">${update_user.getUser_name()}</label>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Nickname:</label>
						<div class="col-lg-8">${update_user.getNick_name()}</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">First name:</label>
						<div class="col-lg-8">${update_user.getFirst_name()}</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Last name:</label>
						<div class="col-lg-8">${update_user.getLast_name()}</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">${update_user.getEmail()}</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Credit Card:</label>
						<div class="col-md-8">${update_user.getCredit_card()}</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">Address:</label>
						<div class="col-md-8">${update_user.getFull_address()}</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-info btn-default"
								name="action" value="edit"> <span></span>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>