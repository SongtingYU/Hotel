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
		// check user type
		if (user != null) {
			if (user.getUser_type() != 2 && user.getUser_type() != 3) {
				String site = new String("elogin.jsp");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}
		} else {
			String site = new String("elogin.jsp");
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
					<button type="button" class="btn btn-default active">Room
						page</button>
				</a>
			</div>
			<div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./userBooking.jsp">
					<button type="button" class="btn btn-default ">User
						booking</button>
				</a>
			</div>

			<!-- <div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./searchBooking.jsp">
					<button type="button" class="btn btn-default">Search booking</button>
				</a>
			</div> -->
		</div>
	</div>

	<br />
	<div class="container">
		<div class="row">
			<div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1"
				ng-app="roomManage" ng-controller="roomManageCtrl">


				<!-- occupied room -->
				<div class="panel panel-default">
					<div class="panel-heading">All occupied room</div>
					<div class="panel-body">
						<div class="table table-responsive">
							<table class="table table-hover">
								<tr>
									<th><h5>Room id:</h5></th>
									<th><h5>hotel name:</h5></th>
									<th><h5>room type:</h5></th>
									<th><h5>room status:</h5></th>
									<th><h5>Check out</h5></th>
								</tr>
								<tr ng-repeat="occupiedRoom in occupiedRooms">
									<td>{{occupiedRoom.room_id}}</td>
									<td>{{occupiedRoom.hotel_name}}</td>
									<td>{{occupiedRoom.room_type}}</td>
									<td>{{occupiedRoom.status}}</td>
									<td><button class="btn btn-default"
											ng-click="roomCheckOut(occupiedRoom)">check out</button></td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<!-- available room -->
				<div class="panel panel-success">
					<div class="panel-heading">All available room</div>
					<div class="panel-body">
						<div class="table table-responsive">
							<table class="table table-hover">
								<tr>
									<th><h5>Room id:</h5></th>
									<th><h5>hotel name:</h5></th>
									<th><h5>room type:</h5></th>
									<th><h5>room status:</h5></th>
									<th><h5>Check in</h5></th>
								</tr>
								<tr ng-repeat="availableRoom in availableRooms">
									<td>{{availableRoom.room_id}}</td>
									<td>{{availableRoom.hotel_name}}</td>
									<td>{{availableRoom.room_type}}</td>
									<td>{{availableRoom.status}}</td>
									<td><button class="btn btn-default"
											ng-click="roomCheckIn(availableRoom)">check in</button></td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div class="panel panel-danger">
					<div class="panel-heading">All maintaining room</div>
					<div class="panel-body">
						<div class="table table-responsive">
							<table class="table table-hover">
								<tr>
									<th><h5>Room id:</h5></th>
									<th><h5>hotel name:</h5></th>
									<th><h5>room type:</h5></th>
									<th><h5>room status:</h5></th>

								</tr>
								<tr ng-repeat="maintainingRoom in maintainingRooms">
									<td>{{maintainingRoom.room_id}}</td>
									<td>{{maintainingRoom.hotel_name}}</td>
									<td>{{maintainingRoom.room_type}}</td>
									<td>{{maintainingRoom.status}}</td>
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
		var app = angular.module('roomManage', []);
		app.controller('roomManageCtrl', function($scope, $http, $window,
				$location) {
			$scope.availableRooms = [];
			$scope.occupiedRooms = [];
			$scope.maintainingRooms = [];
			$scope.responseMSG = "";
			$scope.page = 0;
			$scope.count = 0;

			$scope.getoccupiedRooms = function() {
				var url = './adminController?action=getoccupiedRooms';
				console.log(url);
				$http.get(url).success(function(response) {
					$scope.occupiedRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};
			
			$scope.getmaintainingRooms = function() {
				var url = './adminController?action=getmaintainingRooms';
				console.log(url);
				$http.get(url).success(function(response) {
					$scope.maintainingRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.getAvailableRooms = function() {
				var url = './adminController?action=getAvailableRooms';
				console.log(url);
				$http.get(url).success(function(response) {
					$scope.availableRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.roomCheckIn = function(availableRoom) {
				var url = './adminController?action=roomCheckIn';
				console.log(availableRoom);
				$http.post(url, availableRoom).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
					
				}).error(function(response) {
				});
			};

			$scope.roomCheckOut = function(occupiedRoom) {
				var url = './adminController?action=roomCheckOut';
				console.log(occupiedRoom);
				$http.post(url, occupiedRoom).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
					
				}).error(function(response) {
				});
			};

			$scope.getoccupiedRooms();
			$scope.getAvailableRooms();
			$scope.getmaintainingRooms();

		});
	</script>
</body>
</html>