<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<style>
html, body {
	height: 100%;
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

</head>

<body class="text-center">
	<form class="form-signin" action="/user/edit" method="post">
		<p>
			<font color="red">${signUpErrorMessage}</font>
		</p>
		<h1 class="h3 mb-3 font-weight-normal">Account Informations</h1>
		<div class="form-group">
			<label for="exampleInputEmail1">First Name</label> <input type="text"
				id="firstname" value="${user.firstname}" name="firstname"
				class="form-control">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Last Name</label> <input type="text"
				id="lastname" name="lastname" class="form-control"
				value="${user.lastname}">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">User Name</label> <input type="text"
				id="username" name="username" class="form-control"
				value="${user.username}" readonly="readonly"> <small
				id="emailHelp" class="form-text text-muted">You can't change the user name.</small>

		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Email address</label> <input
				type="email" id="email" name="email" class="form-control"
				value="${user.email}">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" id="password" name="password" class="form-control"
				value="${user.password}">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Shipping address</label> <input
				type="text" id="address" name="address" class="form-control"
				value="${user.address}">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Phone Number</label> <input
				type="text" id="phonenumber" name="phonenumber" class="form-control"
				value="${user.phonenumber}">
		</div>
		<div class="checkbox mb-3"></div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">save</button>
	</form>
</body>
</html>

