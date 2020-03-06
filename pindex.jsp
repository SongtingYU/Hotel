<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="hotel.*" import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="HeaderFile.jsp"%>

<title>Searching Bar</title>
<style type="text/css">
* {
	margin:0;
	padding:0;
}
body {
	font:3px "Microsoft YaHei",Verdana, Arial, Geneva, sans-serif;
	
	background:#d5dfe0;
}
img {
	border-style:none;
}
.main{

}
input{
	border-style:none;
	padding:8px 30px;
	line-height:18px;
	color:#fff;
	font:16px "Microsoft YaHei", Verdana, Geneva, sans-serif;
	cursor:pointer;
	margin-right:60px;}
.btn-style-02 {
	border:1px #028fbd solid;
	-webkit-box-shadow:inset 0px 0px 1px #fff;
	-moz-box-shadow:inset 0px 0px 1px #fff;
	box-shadow:inset 0px 0px 1px #fff;
	-webkit-border-radius:4px;
	-moz-border-radius:4px;
	border-radius:4px;
	text-shadow:1px 1px 0px #45a1d6;
	background-color:#31c0f0;
	background-image: -webkit-gradient(linear, 0 0%, 0 100%, from(#31c0f0), to(#1cabda));
	background-image: -webkit-linear-gradient(top, #31c0f0 0%, #1cabda 100%);
	background-image: -moz-linear-gradient(top, #31c0f0 0%, #1cabda 100%);
	background-image: -ms-linear-gradient(top, #31c0f0 0%, #1cabda 100%);
	background-image: -o-linear-gradient(top, #31c0f0 0%, #1cabda 100%);
	background-image: linear-gradient(top, #31c0f0 0%, #1cabda 100%);
}
.btn-style-02:hover {
	background-color:#1cabda;
	background-image: -webkit-gradient(linear, 0 0%, 0 100%, from(#1cabda), to(#31c0f0));
	background-image: -webkit-linear-gradient(top, #1cabda 0%, #31c0f0 100%);
	background-image: -moz-linear-gradient(top, #1cabda 0%, #31c0f0 100%);
	background-image: -ms-linear-gradient(top, #1cabda 0%, #31c0f0 100%);
	background-image: -o-linear-gradient(top, #1cabda 0%, #31c0f0 100%);
	background-image: linear-gradient(top, #1cabda 0%, #31c0f0 100%);
}

</style>
</head>
<body>
	<%
		String gif_path = request.getContextPath();
		switch ((int) (1 + Math.random() * (5 - 1 + 1)) % 5) {
			case 0 :
				gif_path += "/img/1.jpg";
				break;
			case 1 :
				gif_path += "/img/2.jpg";
				break;
			case 2 :
				gif_path += "/img/3.jpg";
				break;
			case 3 :
				gif_path += "/img/4.jpg";
				break;
			case 4 :
				gif_path += "/img/0.jpg";
				break;
		}
	%>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand">Welcome to 4th Hotel</a>
		</div>
	</div>
	</nav>

	<form action='control' method='POST'>
		<center>
			<br> <br> <br> <br>
			<h4>
				<font color="grey">Please input your PIN number!</font>
			</h4>
			<input type="text" name="PINContent" value=""
				placeholder="PIN Number" style="width: 140px; height: 28px">
			<br>
			<input type="hidden" name="passto" value=<%=request.getParameter("passto")%>>
			
			<div class="main">
				<!--css3自定义渐变圆角按钮样式-->
				<input type="submit" class="btn-style-02" value="Submit" /> <input
					type="hidden" name="action" value="pin" />
				<!--css3自定义渐变圆角按钮样式-->
			</div>
			<br> <br> <img src=<%=gif_path%> alt="" width="1171"
				height="700">
			<script>
			var errori = '<%=request.getParameter("error")%>'
				if (errori == 't') {
					alert("The time remaining is less than 48h!");
					window.location = "Welcome.jsp";
				} else if (errori == 'y') {
					alert("The PIN number is wrong!");
					window.location = "Welcome.jsp";
				}
			</script>
			<br> <br> <br>
			<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; 4TH Hotel 2016</p>
				</div>
			</div>
			</footer>
		</center>
	</form>




	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	</div>
</body>
</html>