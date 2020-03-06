<%@page import="com.comp9321.assign2.User_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.comp9321.assign2.Factory_DAO"%>
<%@page import="com.comp9321.assign2.interface_User_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>Searching Hotel Rooms</title>
</head>
<body>
	Searching Hotel Rooms
	<br />
	<%
		Cookie[] cookies = request.getCookies();
		String Login_user = null;
		if (cookies == null) {
	%><script type="text/javascript">
		javaScript: alert('Please login, thanks.');
		javaScript: window.location.href = 'Sign-up&Sign-in.jsp';
	</script>
	<%
		} else {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String value = cookie.getValue();
				if (value.contains("login")) {
					Login_user = cookie.getName();
					break;
				}
			}
		}
		if (Login_user == null) {
	%><script type="text/javascript">
		javaScript: alert('Please login, thanks.');
		javaScript: window.location.href = 'Sign-up&Sign-in.jsp';
	</script>
	<%
		} else {
			out.print("G'day " + Login_user + ".");
		}
	%>
	<center>
		<form action="<%=request.getContextPath()%>/ControlServlet"
			method="post">

			<select name='searchby'>
				<option>Check-in date and check-out date</option>
				<option>City</option>
				<option>Number of booking rooms</option>
				<option>Maximum price per room per night</option>
			</select>
			<%="&nbsp"%>
			<input type="text" name="stext" style="width: 500px; height: 30px" />
			<%="&nbsp"%>
			<input type='submit' name="action" value='Search it!' />
		</form>
	</center>


</body>
</html>