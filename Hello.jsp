<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="HeaderFile.jsp"%>
<title>Hello</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<br />
	<br />
	<br />
	<div
		class="hero__content page-container page-container-full text-center">
		<h1>Welcome!</h1>
		<small>${sessionScope.username}</small><%--  <small>${username}</small> --%>
		<div class="hello__content hide-sm">
			<%@ include file="SearchBar.jsp"%>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>