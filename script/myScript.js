var app = angular.module('roomManage', []);

app.controller('roomManageCtrl', function($scope, $http, $window, $location) {
	$scope.availableRooms = [];
	$scope.occupiedRooms = [];
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

});