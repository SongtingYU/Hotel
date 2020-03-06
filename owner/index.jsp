<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.unsw.comp9321.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>AirBus</title>
</head>

<body>

	<%@ include file="header.jsp"%>
	
	<%
		userDTO user = (userDTO) session.getAttribute("userSession");
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
			<!-- <div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./adddeal.jsp">
					<button type="button" class="btn btn-default">add deal or
						peak</button>
				</a>
			</div> -->

		</div>
	</div>



	<br />
	<div class="container">
		<div class="row">
			<div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1"
				ng-app="hotelOwner" ng-controller="hotelOwnerCtrl">


				<div class="panel panel-info">
					<div class="panel-heading">Hotel room statistic</div>
					<div class="panel-body">
						<div class="table table-responsive">
							<table class="table table-hover">
								<tr>
									<th><h5>Hotel name:</h5></th>
									<th><h5>Available room</h5></th>
									<th><h5>Occupied Room</h5></th>
									<th><h5>Booking Room</h5></th>
									<th><h5>Maintaining Room</h5></th>
									<th><h5>Action</h5></th>
								</tr>

								<tr ng-repeat="Hotel in Hotels">
									<td>{{Hotel.hotel_name}}</td>
									<td>{{Hotel.available_room_number}}</td>
									<td>{{Hotel.occupied_room_number}}</td>
									<td>{{Hotel.booking_room_number}}</td>
									<td>{{Hotel.maintaining_room_number}}</td>
									<td><a ng-click="setHotelSession(Hotel)"
										href="./viewhotel.jsp?hotel_name={{Hotel.hotel_name}}">
											<button class="btn btn-info">view this hotel</button>
									</a></td>
								</tr>

							</table>
						</div>

					</div>
				</div>

			</div>

		</div>
	</div>

	<!-- <script type="text/javascript" src="script/myScript.js"></script> -->

	<script>
		var app = angular.module('hotelOwner', []);
		app.controller('hotelOwnerCtrl', function($scope, $http, $window,
				$location) {

			$Hotels = [];
			$scope.responseMSG = "";

			// not used yet
			/* $scope.availableRooms = [];
			$scope.occupiedRooms = []; */

			$scope.getHotelsData = function() {
				var url = './ownerController?action=getHotelsData';
				console.log(url);
				$http.get(url).success(function(response) {
					$scope.Hotels = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			//	setHotelSession(Hotel.hotel_name)
			$scope.setHotelSession = function(Hotel) {
				var url = './ownerController?action=setHotelSession';
				$http.post(url, Hotel).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
				}).error(function(response) {
				});
			};

			$scope.getHotelsData();

		});
	</script>
</body>
</html>