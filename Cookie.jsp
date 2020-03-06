<%@ page language="Java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>创建Cookie</title>
</head>

<body>
	<%
		// 创建一个Cookie,包括(key,value).
		Cookie cookie_1 = new Cookie("cookieName", "cookieValue");
		Cookie cookie_2 = new Cookie("Amy", "logout");
		Cookie cookie_3 = new Cookie("Alice", "login");
		Cookie cookie_4 = new Cookie("null", "logout");
		// 设置Cookie的生命周期,如果设置为负值的话,关闭浏览器就失效.
		cookie_1.setMaxAge(-1);//60*60*24*365);
		cookie_2.setMaxAge(-1);
		cookie_3.setMaxAge(-1);
		cookie_4.setMaxAge(-1);
		// 设置Cookie路径,不设置的话为当前路径(对于Servlet来说为request.getContextPath() + web.xml里配置的该Servlet的url-pattern路径部分)
		// cookie.setPath("/"); 

		// 输出Cookie
		response.addCookie(cookie_1);
		response.addCookie(cookie_2);
		response.addCookie(cookie_3);
		response.addCookie(cookie_4);
		// cookie_2.setValue("666s");
		//response.addCookie(cookie_2);
	%>
	已创建Cookie.
	<br>
	<%--  <a href="ShowCookie.jsp">查看Cookie</a> --%>
	<a href="Searching_Hotel_Rooms.jsp">Login Cookie Done</a>
</body>
</html>