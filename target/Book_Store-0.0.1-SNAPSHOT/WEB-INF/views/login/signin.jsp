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
	<form class="form-signin" action="/login" method="post">
		<p>
			<font color="red">${errorMessage}</font>
		</p>
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="username" class="sr-only">User Name</label> <input
			type="text" id="username" name="username" class="form-control"
			placeholder="User Name" required autofocus> <label
			for="password" class="sr-only">Password</label> <input
			type="password" id="password" name="password" class="form-control"
			placeholder="Password" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
	</form>
	<form class="form-signin" action="/signUp" method="post">
		<p>
			<font color="red">${signUpErrorMessage}</font>
		</p>
		<h1 class="h3 mb-3 font-weight-normal">new user</h1>
		<label for="firstName" class="sr-only">first name</label> <input
			type="text" id="firstName" name="firstName" class="form-control"
			placeholder="first name" required> <label for="lastName"
			class="sr-only">last name</label> <input type="text" id="lastName"
			name="lastName" class="form-control" placeholder="last name" required>
		<label for="username" class="sr-only">User Name</label> <input
			type="text" id="username" name="username" class="form-control"
			placeholder="User Name" required autofocus> <label
			for="email" class="sr-only">e-mail address</label> <input
			type="email" id="email" name="email" class="form-control"
			placeholder="e-mail address" required> <label for="password"
			class="sr-only">Password</label> <input type="password" id="password"
			name="password" class="form-control" placeholder="Password" required>
		<label for="address" class="sr-only">shipping address</label> <input
			type="text" id="address" name="address" class="form-control"
			placeholder="shipping address" required> <label
			for="phoneNumber" class="sr-only">phone number</label> <input
			type="text" id="phoneNumber" name="phoneNumber" class="form-control"
			placeholder="phone number" required>
		<div class="checkbox mb-3"></div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">sign
			up</button>
	</form>
</body>
</html>

