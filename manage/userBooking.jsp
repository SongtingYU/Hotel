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
		/*  try {
		
			out.println(user.getUser_name());
		} catch (Exception e) {
		
		}  */
	%>

	<div class="container">
		<div class="row">
			<div class="col-xs-2 col-sm-4 col-md-4 "></div>
			<div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./index.jsp">
					<button type="button" class="btn btn-default">Room page</button>
				</a>
			</div>
			<div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./userBooking.jsp">
					<button type="button" class="btn btn-default active">User
						booking</button>
				</a>
			</div>
			<!-- <div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./searchBooking.jsp">
					<button type="button" class="btn btn-default">Search
						booking</button>
				</a>
			</div> -->
		</div>
	</div>
	<br />

	<div class="container" ng-app="roomManage"
		ng-controller="roomManageCtrl">

		<div class="row">
			<div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1">

				<div class="col-lg-6 col-md-6 col-lg-offset-2 col-md-offset-2">
					<input type="text" class="form-control " id="box"
						placeholder="search..." ng-model="data.content">
				</div>

				<div class="col-lg-2 col-md-2">
					<select id="tag" class="selectpicker form-control"
						data-live-search="true" title="select" ng-model="data.keyword">
						<option value="PIN">PIN</option>
						<option value="email">Email</option>
					</select>
				</div>

				<div class="col-lg-2 col-md-2">
					<button class="btn btn-primary" id="searchBtn" type="button"
						ng-click="searchBooking()">Search</button>
				</div>

			</div>
		</div>

		<br />



		<div class="row">
			<div>
				<div class="col-xs-4 col-sm-4 col-md-4">
					<!--booking room -->
					<div class="panel panel-info">
						<div class="panel-heading">All booking room</div>
						<div class="panel-body">
							<div class="table table-responsive">
								<table class="table table-hover">
									<tr>
										<th><h5>Room id:</h5></th>
										<th><h5>hotel name:</h5></th>
										<th><h5>room type:</h5></th>
										<th><h5>room status:</h5></th>
										<!-- <th><h5>Check out</h5></th> -->
									</tr>
									<tr ng-repeat="bookingRoom in bookingRooms">
										<td>{{bookingRoom.room_id}}</td>
										<td>{{bookingRoom.hotel_name}}</td>
										<td>{{bookingRoom.room_type}}</td>
										<td>{{bookingRoom.status}}</td>
										<!-- <td><button class="btn btn-default"
											ng-click="roomCheckOut(bookingRoom)">check out</button></td> -->
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

				</div>

				<div class="col-xs-8 col-sm-8 col-md-8">
					<!-- all booking status -->
					<div class="panel panel-info">
						<div class="panel-heading">User booking</div>
						<div class="panel-body">
							<div class="table table-responsive">
								<table class="table table-hover">
									<tr>
										<th><h5>booking id:</h5></th>
										<th><h5>room id:</h5></th>
										<th><h5>room type:</h5></th>
										<th><h5>price:</h5></th>
										<th><h5>check in date:</h5></th>
										<th><h5>check out date:</h5></th>
										<th><h5>Extra bed:</h5></th>
										<th><h5>PIN:</h5></th>
										<th><h5>action</h5></th>
										<th><h5>Change room</h5></th>
										<th><h5>Delete</h5></th>
									</tr>


									<tr ng-repeat="userBooking in userBookings">
										<td>{{userBooking.booking_id}}</td>
										<td>{{userBooking.booking_room_id}}</td>
										<td>{{userBooking.room_type}}</td>
										<td>{{userBooking.booking_confirm_price}}</td>
										<td>{{userBooking.check_in_date}}</td>
										<td>{{userBooking.check_out_date}}</td>
										<td>{{userBooking.extra_bed}}</td>
										<td>{{userBooking.PIN}}</td>
										<!--<a href="./userBooking.jsp">
											<button type="button" class="btn btn-default active">User
												booking</button>
										</a> -->
										<td>
											<button class="btn btn-default"
												ng-click="bookingCheckin(userBooking)">Confirm</button>
										</td>

										<!-- var url = "searchBooking.jsp?content=" + $scope.data.content     
											+ "&keyword=" + $scope.data.keyword;-->
										<td><a
											href="./searchBooking.jsp?content={{userBooking.booking_id}}&keyword=booking_id">
												<button class="btn btn-info">edit</button>
										</a></td>

										<td>
											<button class="btn btn-danger"
												ng-click="bookingDelete(userBooking)">remove</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>



	<script>
		var app = angular.module('roomManage', []);
		app.controller('roomManageCtrl', function($scope, $http, $window,
				$location) {
			$scope.availableRooms = [];
			$scope.occupiedRooms = [];
			$scope.bookingRooms = [];

			$scope.userBookings = [];

			$scope.responseMSG = "";

			$scope.data = {
				content : null,
				keyword : "PIN",
			};

			$scope.getuserBooking = function() {
				var url = './adminController?action=getuserBooking';
				console.log(url);
				$http.get(url).success(
						function(response) {

							$scope.userBookings = response;
							$scope.responseMSG = "success";
							/* console.log($scope.userBookings[0].booking_id
									+ $scope.responseMSG); */
						}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.getbookingdRooms = function() {
				var url = './adminController?action=getbookingRooms';
				console.log(url);
				$http.get(url).success(function(response) {
					$scope.bookingRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

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

			$scope.searchBooking = function($location) {
				var url = "searchBooking.jsp?content=" + $scope.data.content
						+ "&keyword=" + $scope.data.keyword;
				console.log(url);
				$window.location.href = url;
			}

			$scope.roomCheckIn = function(availableRoom) {
				var url = './adminController?action=roomCheckIn';
				console.log(availableRoom);
				$http.post(url, availableRoom).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
					$scope.getbookingdRooms();
				}).error(function(response) {
				});
			};

			$scope.roomCheckOut = function(occupiedRoom) {
				var url = './adminController?action=roomCheckOut';
				console.log(occupiedRoom);
				$http.post(url, occupiedRoom).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
					$scope.getbookingdRooms();
				}).error(function(response) {
				});
			};

			$scope.bookingCheckin = function(userBooking) {
				var url = './adminController?action=bookingCheckin';
				console.log("userBooking checkin :111: " + userBooking);
				$http.post(url, userBooking).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
					$scope.getbookingdRooms();
					$scope.getuserBooking();
				}).error(function(response) {
				});
			};

			$scope.bookingDelete = function(userBooking) {
				var url = './adminController?action=bookingDelete';
				console.log("userBooking checkin :111: " + userBooking);
				$http.post(url, userBooking).success(function(response) {
					$scope.getoccupiedRooms();
					$scope.getAvailableRooms();
					$scope.getbookingdRooms();
					$scope.getuserBooking();
				}).error(function(response) {
				});
			};

			$scope.getoccupiedRooms();
			$scope.getAvailableRooms();
			$scope.getbookingdRooms();
			$scope.getuserBooking();

		});
	</script>
</body>
</html>