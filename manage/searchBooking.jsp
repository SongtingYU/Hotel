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
					<button type="button" class="btn btn-default">Room page</button>
				</a>
			</div>
			<div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./userBooking.jsp">
					<button type="button" class="btn btn-default">User booking</button>
				</a>
			</div>
			<!-- <div class="col-xs-4 col-sm-2 col-md-2 ">
				<a href="./searchBooking.jsp">
					<button type="button" class="btn btn-default active">Search
						booking</button>
				</a>
			</div> -->
		</div>
	</div>
	<br />

	<div class="container" ng-app="bookingSearchApp"
		ng-controller="bookingSearchCtrl">

		<div class="row">
			<div class="col-lg-10 col-md-10 col-lg-offset-1 col-md-offset-1">

				<!-- <div class="col-lg-6 col-md-6 col-lg-offset-2 col-md-offset-2">
					<input type="text" class="form-control " id="box"
						placeholder="search..." ng-model="data.content">
				</div>

				<div class="col-lg-2 col-md-2">
					<select id="tag" value="PIN" class="selectpicker form-control"
						data-live-search="true" title="select" ng-model="data.keyword">
						<option value="PIN">PIN</option>
						<option value="user_name">User name</option>
						<option value="email">Email</option>
					</select>
				</div>

				<div class="col-lg-2 col-md-2">
					<button class="btn btn-primary" id="searchBtn" type="button"
						ng-click="searchBooking()">Search</button>
				</div> -->

			</div>
		</div>

		<br />


		<div class="row">
			<div>
				<div class="col-xs-4 col-sm-4 col-md-4">


					<!--booking room -->
					<div class="panel panel-info">
						<div class="panel-heading">Original booking room</div>
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
									<tr ng-repeat="originalBookingRoom1 in originalBookingRoom">
										<td>{{originalBookingRoom1.room_id}}</td>
										<td>{{originalBookingRoom1.hotel_name}}</td>
										<td>{{originalBookingRoom1.room_type}}</td>
										<td>{{originalBookingRoom1.status}}</td>
										<!-- <td><button class="btn btn-default"
											ng-click="roomCheckOut(bookingRoom)">check out</button></td> -->
									</tr>
								</table>
							</div>
						</div>
					</div>


					<!-- available room -->
					<div class="panel panel-success">
						<div class="panel-heading">Others available room</div>
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
									<tr ng-repeat="otherAvailableRoom in otherAvailableRooms">
										<td>{{otherAvailableRoom.room_id}}</td>
										<td>{{otherAvailableRoom.hotel_name}}</td>
										<td>{{otherAvailableRoom.room_type}}</td>
										<td>{{otherAvailableRoom.status}}</td>
										<td><button class="btn btn-default"
												ng-click="roomCheckIn(availableRoom)">check in</button></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-8 col-sm-8 col-md-8">
					<!-- all booking status -->
					<div class="panel panel-info">
						<div class="panel-heading">Your booking</div>
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
										<!-- <th><h5>Change room</h5></th> -->
										<th><h5>Delete</h5></th>
									</tr>


									<tr ng-repeat="Booking in searchBookings">
										<td>{{Booking.booking_id}}</td>
										<td>{{Booking.booking_room_id}}</td>
										<td>{{Booking.room_type}}</td>
										<td>{{Booking.booking_confirm_price}}</td>
										<td>{{Booking.check_in_date}}</td>
										<td>{{Booking.check_out_date}}</td>
										<td>{{Booking.extra_bed}}</td>
										<td>{{Booking.PIN}}</td>
										<!--<a href="./userBooking.jsp">
											<button type="button" class="btn btn-default active">User
												booking</button>
										</a> -->
										<td>
											<button class="btn btn-default"
												ng-click="bookingCheckin(Booking)">Confirm</button>
										</td>

										<!-- 	<td><a
											href="./searchBooking.jsp?content={{userBooking.PIN}}&keyword=PIN">
												<button class="btn btn-info">edit</button>
										</a></td> -->

										<td>
											<button class="btn btn-danger"
												ng-click="bookingDelete(Booking)">remove</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>



					<!-- all booking status -->
					<div class="panel panel-warning">
						<div class="panel-heading">Change room</div>
						<div class="panel-body">

							<form class="form-inline" role="form">
								<div class="form-group">
									<label>Booking ID:</label> <input type="text"
										class="form-control" ng-model="ChangeRoomData.booking_id"
										style="width: 100px" placeholder="booking id">
								</div>
								<div class="form-group">
									<label>change room to</label> <input type="text"
										class="form-control" ng-model="ChangeRoomData.room_id"
										style="width: 100px" placeholder="room id">
								</div>
								<button class="btn btn-primary" ng-click="changeRoom()"
									data-toggle="modal" data-target="#myModal">OK</button>
							</form>

							<div id="myModal" class="modal fade" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">

										<div class="modal-body" style="color: #111">
											<p>Change success</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	</div>

	<script>
		var app = angular.module('bookingSearchApp', []);
		app.controller('bookingSearchCtrl', function($scope, $http, $window,
				$location) {

			$scope.originalBookingRoom = [];
			$scope.otherAvailableRooms = [];
			$scope.searchBookings = [];
			$scope.responseMSG = "";
			$scope.content = null;
			$scope.data = {
				content : null,
				keyword : null,
			};
			$scope.ChangeRoomData = {
				booking_id : null,
				room_id	   : null,
			};
			
			$scope.changeRoom = function(){
				var url = './searchController?action=changeRoom';
				console.log($scope.ChangeRoomData);
				$http.post(url, $scope.ChangeRoomData).success(function(response){
					$scope.getBookingSearch();
				}).error(function(response) {
					$scope.responseMSG = response;
				});
			};
			

			$scope.getParaVal = function(param) {
				$scope.result = $window.location.search.match(new RegExp(
						"(\\?|&)" + param + "(\\[\\])?=([^&]*)"));
				return $scope.result ? $scope.result[3] : false;
			};

			//get original booking room
			$scope.getoriginalBookingRoom = function() {
				var url = './searchController?action=getoriginalBookingRoom';
				$http.get(url).success(function(response) {
					$scope.originalBookingRoom = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.getOtherAvailableRooms = function() {
				var url = './searchController?action=getOtherAvailableRooms';
				$http.get(url).success(function(response) {
					$scope.otherAvailableRooms = response;
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};

			$scope.initPara = function() {
				$scope.data.content = $scope.getParaVal("content");
				$scope.data.keyword = $scope.getParaVal("keyword");
				$scope.getBookingSearch();
			};

			$scope.getBookingSearch = function() {
				var url = './searchController?action=searchBooking';
				$http.post(url, $scope.data).success(function(response) {
					$scope.searchBookings = response;
					console.log($scope.searchBookings);

					$scope.getoriginalBookingRoom();
					console.log("hellpoo");
					$scope.getOtherAvailableRooms();
					console.log("hi");
					$scope.responseMSG = "success";
				}).error(function(response) {
					$scope.responseMSG = "failed";
				});
			};
			
			$scope.bookingCheckin = function(Booking) {
				var url = './adminController?action=bookingCheckin';
				console.log("Booking checkin :111: " + Booking);
				$http.post(url, Booking).success(function(response) {
					$scope.getoriginalBookingRoom();
					$scope.getOtherAvailableRooms();
					$scope.getBookingSearch();
				}).error(function(response) {
				});
			};
			
			$scope.bookingDelete = function(Booking) {
				var url = './adminController?action=bookingDelete';
				console.log("userBooking checkin :111: " + Booking);
				$http.post(url, Booking).success(function(response) {
					$scope.getoriginalBookingRoom();
					$scope.getOtherAvailableRooms();
					$scope.getBookingSearch();
				}).error(function(response) {
				});
			};

			$scope.initPara();
			/* $scope.getoriginalBookingRoom(); */
		});
	</script>
</body>
</html>