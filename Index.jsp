<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>

<%@ include file="HeaderFile.jsp"%>
<title>Index</title>
</head>
<body>

	<!-- head bar -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="Welcome_container">
			<div class="navbar-header">
				<a class="navbar-brand">Welcome to 4th Hotel</a>
			</div>

			<div id="navbar" class="navbar-collapse collapse">

				<form name="validateLogin" onsubmit="return Login()"
					class="navbar-form navbar-right" action="control" method="POST">
					<div class="text-danger">${error}</div>
					<div class="form-group">

						<input type="text" class="form-control" placeholder="Username"
							name="user_name" required pattern=".{4,}"
							title="Four or more characters">/>
					</div>

					<div class="form-group">
						<input type="password" class="form-control" placeholder="Password"
							name="password" required pattern=".{4,}"
							title="Four or more characters" />
					</div>
					<input type="submit" class="btn btn-primary" name="action"
						value="Signin" />
				</form>

			</div>
		</div>
	</nav>

	<%@ include file="register.jsp"%>
	<%@ include file="footer.jsp"%>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>