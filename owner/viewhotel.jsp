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
		//String hotel_name = (String) session.getAttribute("searchHotel");
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

			out.println(user.getUser_name() + hotel_name);
			out.println("<br/>");
			out.println(user.getUser_name() + hotel_name2);
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
	<br />

	<div class="container" ng-app="hotelManage"
		ng-controller="hotelManageCtrl">

		<div class="row">
			<div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1">



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
									<th><h5>Send to repair</h5></th>
								</tr>
								<tr ng-repeat="availableRoom in availableRooms">
									<td>{{availableRoom.room_id}}</td>
									<td>{{availableRoom.hotel_name}}</td>
									<td>{{availableRoom.room_type}}</td>
									<td>{{availableRoom.status}}</td>
									<td><button class="btn btn-danger"
											ng-click="sendRoomToRepair(availableRoom)">repair
											the room</button></td>
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
									<th><h5>room available:</h5></th>
								</tr>
								<tr ng-repeat="maintainRoom in maintainRooms">
									<td>{{maintainRoom.room_id}}</td>
									<td>{{maintainRoom.hotel_name}}</td>
									<td>{{maintainRoom.room_type}}</td>
									<td>{{maintainRoom.status}}</td>
									<td><button class="btn btn-danger"
											ng-click="roomFinishMaintain(maintainRoom)">finish
											repair</button></td>
								</tr>
							</table>
						</div>
					</div>
				</div>


				<!-- occupied room -->
				<div class="panel panel-default">
					<div class="panel-heading">All occupied room or booking room</div>
					<div class="panel-body">
						<div class="table table-responsive">
							<table class="table table-hover">
								<tr>
									<th><h5>Room id:</h5></th>
									<th><h5>hotel name:</h5></th>
									<th><h5>room type:</h5></th>
									<th><h5>room status:</h5></th>

								</tr>
								<tr ng-repeat="occupiedRoom in occupiedRooms">
									<td>{{occupiedRoom.room_id}}</td>
									<td>{{occupiedRoom.hotel_name}}</td>
									<td>{{occupiedRoom.room_type}}</td>
									<td>{{occupiedRoom.status}}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

	

	<script>
		var app = angular.module('hotelManage', []);
		app.controller('hotelManageCtrl', function($scope, $http, $window,
				$location) {
			$scope.availableRooms = [];
			$scope.occupiedRooms = [];
			$scope.maintainRooms = [];
			$scope.responseMSG = "";
			$scope.data = {
				hotel_name : null,
			};

			$scope.getParaVal = function(param) {
				$scope.result = $window.location.search.match(new RegExp(
						"(\\?|&)" + param + "(\\[\\])?=([^&]*)"));
				return $scope.result ? $scope.result[3] : false;
			};

			$scope.initPara = function() {
				$scope.data.hotel_name = $scope.getParaVal("hotel_name");
				console.log($scope.data);
				$scope.getoccupiedRooms();
				$scope.getavailableRooms();
				$scope.getmaintainRooms();
			};

			$scope.getoccupiedRooms = function() {
				var url = './ownerController?action=getoccupiedRooms';
				console.log(url);
				$http.post(url, $scope.data).success(function(response) {
					$scope.occupiedRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.getmaintainRooms = function() {
				var url = './ownerController?action=getmaintainRooms';
				console.log(url);
				$http.post(url, $scope.data).success(function(response) {
					$scope.maintainRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.getavailableRooms = function() {
				var url = './ownerController?action=getavailableRooms';
				console.log(url);
				$http.post(url, $scope.data).success(function(response) {
					$scope.availableRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.sendRoomToRepair = function(availableRoom) {
				var url = './ownerController?action=roomRepair';
				console.log(availableRoom);
				$http.post(url, availableRoom).success(function(response) {
					$scope.getmaintainRooms();
					$scope.getoccupiedRooms();
					$scope.getavailableRooms();
				}).error(function(response) {
				});
			};

			$scope.roomFinishMaintain = function(maintainRoom) {
				var url = './ownerController?action=roomFinishMaintain';
				console.log(maintainRoom);
				$http.post(url, maintainRoom).success(function(response) {
					$scope.getmaintainRooms();
					$scope.getoccupiedRooms();
					$scope.getavailableRooms();
				}).error(function(response) {
				});
			};

			$scope.initPara();

			$scope.getAvailableRooms = function() {
				var url = './ownerController?action=getAvailableRooms';
				console.log(url);
				$http.get(url).success(function(response) {
					$scope.availableRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

		});
	</script>



</body>
</html>