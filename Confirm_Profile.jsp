<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.comp9321.assign2.*"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="HeaderFile.jsp"%>
<title>Edit Profile</title>
</head>
<body>
	<%
		User_Bean user_info = request.getAttribute("payment_user") == null ? new User_Bean()
				: (User_Bean) request.getAttribute("payment_user");
	%>
	<%@ include file="Header.jsp"%>
	<div class="container">
		<div class="row">
			<!-- left column -->
			<div class="col-md-2"></div>

			<!-- edit form column -->
			<div class="col-md-8 personal-info">
				<h3>Confirm Payment Information</h3>
				<div class="text-danger">${PasswordError}</div>

				<form class="form-horizontal" action="control" method="POST">
					<div class="form-group">
						<label class="col-lg-3 control-label">User name:</label> <input
							type="hidden" name="user_name"
							value="<%=user_info.getUser_name()%>" />
						<div class="col-lg-8">
							<%=user_info.getUser_name()%>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Nick name:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control"
								value="<%=user_info.getNick_name()%>" name="nick_name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">First name:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control"
								value="<%=user_info.getFirst_name()%>" name="first_name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Last name:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control"
								value="<%=user_info.getLast_name()%>" name="last_name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control"
								value="<%=user_info.getEmail()%>" name="email" required
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
								title="Invalid Email Format" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Credit Card:</label>
						<div class="col-md-8">
							<input type="text" class="form-control"
								value="<%=user_info.getCredit_card()%>" name="credit_card"
								required pattern="[0-9]{16}" title="Invlid card number" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Address:</label>
						<div class="col-md-8">
							<input type="text" class="form-control"
								value="<%=user_info.getFull_address()%>" name="full_address" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Password:</label>
						<div class="col-md-8">
							<input type="password" class="form-control"
								value="<%=user_info.getPassword()%>" name="password" required
								pattern=".{4,}" title="Invlid Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Confirm password:</label>
						<div class="col-md-8">
							<input type="password" class="form-control"
								value="<%=user_info.getPassword()%>"
								name="password_confirmation" required pattern=".{4,}"
								title="Invlid Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-primary" name="action"
								value="Save & Pay"> <span></span>
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