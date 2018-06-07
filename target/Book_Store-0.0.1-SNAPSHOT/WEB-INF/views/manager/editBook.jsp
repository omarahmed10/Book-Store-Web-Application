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
	<form class="form-signin" action="/manager/add" method="post">
		<p>
			<font color="red">${addingBookErrorMsg}</font>
		</p>
		<h1 class="h3 mb-3 font-weight-normal">Book Informations</h1>
		<div class="form-group">
			<label for="exampleInputEmail1">ISBN</label> <input type="text"
				id="isbn" name="isbn" class="form-control" readonly="readonly" value="${book.isbn }>
			<small id="emailHelp" class="form-text text-muted"">You can't
				change the book isbn.</small>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> <input type="text"
				id="title" name="title" class="form-control" readonly="readonly" value="${book.title }">
			<small id="emailHelp" class="form-text text-muted">You can't
				change the book title.</small>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Publisher name</label> <input
				type="text" id="publishername" name="publishername"
				class="form-control" value="${book.publishername }">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Publish Year</label> <input
				type="date" id="pubyear" name="pubyear" class="form-control" value="${book.pubyear }">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Price</label> <input type="number"
				id="price" name="price" class="form-control" value="${book.price }">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">number of available copies</label>
			<input type="number" id="copiesnums" name="copiesnums"
				class="form-control" value="${book.copiesnums }">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Threshold</label><input type="text"
				id="threshold" name="threshold" class="form-control" required value="${book.threshold }"><small
				id="emailHelp" class="form-text text-muted">The minimum
				quantity in stock.</small>

		</div>
		<div class="form-group">
			<label for="exampleFormControlSelect1">Category</label> <select
				class="form-control" id="category" name="category" ">
				<option>Science</option>
				<option>Art</option>
				<option>Religion</option>
				<option>History</option>
				<option>Geography</option>
			</select>
		</div>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">Authors</label><input
				class="form-control form-control-lg" type="text" id="authors"
				name="authors" maxlength="255" value="${book.authors }"><small id="emailHelp"
				class="form-text text-muted">The input has to be separated
				by commas(,)</small>
		</div>
		<div class="checkbox mb-3"></div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">save</button>
	</form>
</body>
</html>

