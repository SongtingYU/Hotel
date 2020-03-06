<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="HeaderFile.jsp"%>
<title></title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<form action="control" method="POST">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="Hello.jsp">4th Hotel</a>
				</div>
				<%
					if (session.getAttribute("username") != null) {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown"><b>G'day ${sessionScope.username} !</b></a>
						<ul class="dropdown-menu">
							<li><a href="control?action=Profile">Profile<span
									class="glyphicon glyphicon-user pull-right"></span></a></li>
							<li class="divider"></li>
							<li><a href="control?action=edit">Settings<span
									class="glyphicon glyphicon-cog pull-right"></span></a></li>
							<li class="divider"></li>
							<li><a href="control?action=Shopping_Cart">Shopping Cart<span
									class="glyphicon glyphicon-tasks pull-right"></span></a></li>
							<li class="divider"></li>
							<li><a href="control?action=Logout">Sign Out <span
									class="glyphicon glyphicon-log-out pull-right"> </span></a></li>
						</ul></li>

				</ul>
				<%
					}
				%>
			</div>
		</form>
	</nav>
</body>
</html>