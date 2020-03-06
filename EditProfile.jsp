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
	<%@ include file="Header.jsp"%>
	<div class="container">
		<div class="row">
			<!-- left column -->
			<div class="col-md-2"></div>

			<!-- edit form column -->
			<div class="col-md-8 personal-info">
				<h3>Edit Profile info</h3>
				<div class="text-danger">${PasswordError}</div>

				<form class="form-horizontal" action="control" method="POST">
					<div class="form-group">
						<label class="col-lg-3 control-label">User name:</label> <input
							type="hidden" name="user_name" value="${user_name }" />
						<div class="col-lg-8">${user_name}</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Nick name:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" value="${nick_name}"
								name="nick_name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">First name:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" value="${first_name}"
								name="first_name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Last name:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" value="${last_name}"
								name="last_name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input type="text" class="form-control" value="${email}"
								name="email" required
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
								title="Invalid Email Format" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Credit Card:</label>
						<div class="col-md-8">
							<input type="text" class="form-control" value="${credit_card}"
								name="credit_card"
								<%-- <%if (  session.getAttribute("credit_card").matches("[0-9]{16}")  ) {%>
								required pattern="[0-9]{16}" title="Invlid card number" <%}%>  --%>/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Address:</label>
						<div class="col-md-8">
							<input type="text" class="form-control" value="${full_address}"
								name="full_address" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Password:</label>
						<div class="col-md-8">
							<input type="password" class="form-control" value="${password}"
								name="password" required pattern=".{4,}" title="Invlid Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Confirm password:</label>
						<div class="col-md-8">
							<input type="password" class="form-control" value="${password}"
								name="password_confirmation" required pattern=".{4,}"
								title="Invlid Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-primary" name="action"
								value="Save"> <span></span> <input type="reset"
								class="btn btn-default" value="Reset">
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