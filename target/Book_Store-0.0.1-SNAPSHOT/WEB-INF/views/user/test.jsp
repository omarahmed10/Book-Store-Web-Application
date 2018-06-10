<!DOCTYPE html>
<html data-ng-app="formSubmit">
<head>
<meta charset="ISO-8859-1">
<title>Pass JSON Array AngularJS Spring MVC Controller</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js"></script>
<script type="text/javascript">
	var app = angular.module('formSubmit', []);
	app
			.controller(
					'FormSubmitController',
					[
							'$scope',
							'$http',
							function($scope, $http) {

								$scope.list = [];
								$scope.formData = [ {
									"isbn" : "12341234512",
									"title" : "NY",
									"copiesnums" : "123456"
								}, {
									"isbn" : "451231235",
									"title" : "NY2",
									"copiesnums" : "123456789"
								} ];
								$scope.submit = function() {
									var response = $http.get(
											'cart',
											$scope.formData);
									response.success(function(data, status,
											headers, config) {
										console.log(data);
										$scope.list.push(data);
									});
									response.error(function(data, status,
											headers, config) {
										alert("Exception details: "
												+ JSON.stringify({
													data : data
												}));
									});

								};
							} ]);
</script>
</head>
<body>
	<form data-ng-submit="submit()"
		data-ng-controller="FormSubmitController">
		{{formData}}<br> <br> <input type="submit" id="submit"
			value="Submit " /><br>

		<h4>You submitted below data through post:</h4>
		<pre>Data ={{list}}</pre>
	</form>
</body>
</html>