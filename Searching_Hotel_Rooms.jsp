<%@page import="com.comp9321.assign2.User_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.comp9321.assign2.Factory_DAO"%>
<%@page import="com.comp9321.assign2.interface_User_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html lang="en">

<head>
<%@ include file="HeaderFile.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Heroic Features - Start Bootstrap Template</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/heroic-features.css" rel="stylesheet">

</head>


<body
	style="background-image: img/intro-bg.jpg; background-position: center; background-repeat: repeat-y">
	<%
		Cookie[] cookies = request.getCookies();
		String Login_user = null;
		if (cookies == null) {
	%><script type="text/javascript">
		javaScript: alert('Please login, thanks.');
		javaScript: window.location.href = 'Index.jsp';
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
		javaScript: window.location.href = 'Index.jsp';
	</script>
	<%
		} else {
			out.print("G'day " + Login_user + ".");
		}
	%>



	<!-- Page Content -->
	<div class="container">
		<div class="row text-center">



			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<img src="http://placehold.it/800x500" alt="">
					<div class="caption">
						<h3>Sydney</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<img src="http://placehold.it/800x500" alt="">
					<div class="caption">
						<h3>Brisbane</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<img src="http://placehold.it/800x500" alt="">
					<div class="caption">
						<h3>Melbourne</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					</div>
				</div>
			</div>

		</div>
		<!-- /.row -->
		<!-- Jumbotron Header -->
		<header class="jumbotron hero-spacer">
			<div class="text-center">
				<%@ include file="SearchBar.jsp"%>
			</div>
		</header>
		<!-- Page Features -->
		<div class="row text-center">

			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<img src="http://placehold.it/800x500" alt="">
					<div class="caption">
						<h3>Perth</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<img src="http://placehold.it/800x500" alt="">
					<div class="caption">
						<h3>Adelaide</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 col-sm-6 hero-feature">
				<div class="thumbnail">
					<img src="http://placehold.it/800x500" alt="">
					<div class="caption">
						<h3>Hobart</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
					</div>
				</div>
			</div>

		</div>
		<!-- /.row -->


		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; 4TH Hotel 2016</p>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
