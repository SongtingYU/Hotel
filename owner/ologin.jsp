<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuickSearch</title>
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="container-full" ng-app="login"
		ng-controller="customersCtrl">
		<div class="wrapper">
			<div class="content">
				<div id="form_wrapper" class="form_wrapper">

					<form class="login active">
						<h3 style="margin-top: 0">Owner Login</h3>
						<div>
							<label>Username:</label> <input type="text" class="form-control"
								ng-model="login.user_name" required>
						</div>

						<div>
							<label>Password: </label> <input type="password"
								class="form-control" ng-model="login.password" required>
						</div>

						<div class="button" style="margin-bottom: 0">
							<input type="submit" value="Login" class="btn btn-default"
								ng-click="submit()"></input>
								<br/><br/><br/>
							<!-- <a href="signup.jsp"
								class="linkform">Don't have an account? Register Now</a> -->

							<div id="myModal" class="modal fade" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>

											<h4 class="modal-title" style="color: #111">Invalid
												username or password</h4>
										</div>
										<div class="modal-body" style="color: #111">
											<p>Login Failed</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>
						</div>

					</form>
				</div>
			</div>

		</div>
	</div>

	<script>
		var app = angular.module('login', []);
		app
				.controller(
						'customersCtrl',
						function($scope, $window, $http) {
							$scope.login = {
								user_name : "",
								password : ""
							};
							$scope.responseMSG = "";
							$scope.submit = function() {
								$http
										.post('./ownerController?action=login',
												$scope.login)
										.success(

												function(response) {
													$scope.responseMSG = response;
													console
															.log($scope.responseMSG);
													if (response === 'login successed') {
														$window.location.href = '../owner/index.jsp'
													} else {
														console.log("111111");
														$('#myModal').modal(
																'show');
													}
												}).error(function(response) {
											$scope.responseMSG = response;
										});
							};
						});
	</script>

</body>
</html>