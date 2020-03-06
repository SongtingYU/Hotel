<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.unsw.comp9321.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuickSearch</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%
		userDTO user = (userDTO) session.getAttribute("userSession");
		String hotel_name2 = (String) session.getAttribute("selectedHotel");
		// check user type
		if (user != null) {
			if (user.getUser_type() != 3) {
				String site = new String("ologin.jsp");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}
		} else {
			String site = new String("ologin.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
		/* try {

			//out.println(user.getUser_name());
			//out.println(user.getUser_name() + hotel_name2);
		} catch (Exception e) {

		} */
	%>

	<div class="container">
		<div class="row">
			<div class="col-xs-2 col-sm-4 col-md-4 "></div>
			<div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./index.jsp">
					<button type="button" class="btn btn-default">hotel page</button>
				</a>
			</div>
			<div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./adddeal.jsp">
					<button type="button" class="btn btn-default">add deal or
						peak</button>
				</a>
			</div>

		</div>
	</div>

	<div class="container">
		<div ng-app="adddealAPP" ng-controller="formCtrl">
			<div class="row">
				<div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h1>
								<small>add deal or peak time</small>
							</h1>
						</div>
						<div class="panel-body">

							<form name="myForm" role="form" class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="hotelname">hotel name:</label>

									<div class="col-sm-5">
										<%
											out.println("<p style = \"margin-top: 7px;\">" + hotel_name2 + "</p>");
										%>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="deal_name">Special or peak Name:</label>

									<div class="col-sm-5">
										<input type="text" name="deal_name" id="deal_name"
											ng-model="deal.deal_name" class="form-control"
											placeholder="Enter a deal or peak Name">
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="deal_type">type:</label>
									<div class="col-sm-5">
										<select class="form-control" name="deal_type" id="deal_type"
											ng-model="deal.deal_type" required>
											<option value="special">Special deal</option>
											<option value="peak">Peak period</option>
										</select>

									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="room_type">room type:</label>
									<div class="col-sm-6" style="margin-top: 7px">
										<input type="checkbox" ng-model="deal.Single"> Single
										<input type="checkbox" ng-model="deal.Twin"> Twin <input
											type="checkbox" ng-model="deal.Queen"> Queen <input
											type="checkbox" ng-model="deal.Executive"> Executive
										<input type="checkbox" ng-model="deal.Suite"> Suite

									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="percentage">Percentage:</label>

									<div class="col-sm-5">
										<input type="text" name="percentage" id="percentage"
											ng-model="deal.percentage" class="form-control"
											placeholder="Enter a number" required>
									</div>
									<p style="margin-top: 7px">%</p>

								</div>


								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="start_date">Start date:</label>

									<div class="col-sm-5">
										<input type="text" name="start_date" id="start_date"
											ng-model="deal.start_date" class="form-control"
											placeholder="YYYY-MM-DD" required>
									</div>

								</div>

								<div class="form-group">
									<label class="control-label col-sm-offset-1 col-sm-3"
										for="end_date">End date:</label>

									<div class="col-sm-5">
										<input type="text" name="end_date" id="end_date"
											ng-model="deal.end_date" class="form-control"
											placeholder="YYYY-MM-DD" required>
									</div>

								</div>

								<div class="form-group">

									<div class="col-sm-offset-5">
										<button class="btn" ng-click="addSpecialAlert()" type="submit">submit</button>
									</div>


									<div id="myModal2" class="modal fade" role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>

													<h4 class="modal-title">{{deal.deal_type}} confirm</h4>
												</div>
												<div class="modal-body">
													<p>
														Hotel:
														<%
														out.println(hotel_name2);
													%>
													</p>
													<p>{{deal.deal_type}} name: {{deal.deal_name}}</p>
													<p>Percentage: {{deal.percentage}}</p>
													<p>Start date: {{deal.start_date}}</p>
													<p>End date : {{deal.end_date}}</p>
												</div>

												<div class="modal-footer">
													<button class="btn" ng-click="checknew()" type="submit"
														data-dismiss="modal">confirm</button>
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
		</div>
	</div>





	<script>
		var app = angular.module('adddealAPP', []);
		app.controller('formCtrl', function($scope, $http, $window, $location) {
			$scope.deal = {
				"deal_name" : "",
				"deal_type" : "special",
				"Single" : false,
				"Twin" : false,
				"Queen" : false,
				"Executive" : false,
				"Suite" : false,
				"percentage" : "",
				"start_date" : "",
				"end_date" : "",
			};
			$scope.returnMG = "";

			$scope.checknew = function() {
				console.log($scope.deal);
				var url = './ownerController?action=addDeal';
				$http.post(url, $scope.deal).success(function(response) {
					
					$scope.returnMG = response;
					console.log($scope.returnMG);
					if ($scope.returnMG == "success") {
						alert("success!");
					}
					$window.location.reload();
				}).error(function(response) {
					$scope.returnMG = response;
					console.log($scope.returnMG);
					if ($scope.returnMG == "failed") {
						alert("doesn't success");
					}
					$window.location.reload();
				});
			};

			$scope.addSpecialAlert = function() {
				$('#myModal2').modal('show');
			};

		});
	</script>


</body>
</html>