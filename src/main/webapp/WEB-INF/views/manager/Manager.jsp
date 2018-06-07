<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<!-- Custom styles for this template -->

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
</head>

<body>
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
	<span class="navbar-brand col-sm-3 col-md-2 mr-0">Book Store</span>
	<div class="navbar-collapse offcanvas-collapse"
		id="navbarsExampleDefault">
		<form class="form-inline my-2 my-lg-0" action="/manager/search">
			<select id="inputState" name="attribute"
				class="form-control form-control-dark mr-sm-2">
				<option selected>Title</option>
				<option>Category</option>
				<option>Author</option>
			</select> <input class="form-control form-control-dark mr-sm-2" type="text"
				placeholder="Search" aria-label="Search" name="text">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>

	</div>
	<ul class="navbar-nav px-3">
		<li class="nav-item text-nowrap"><spring:url value="/logout"
				var="logoutURL" /> <a class="nav-link" href="${logoutURL }">Log
				out</a></li>
	</ul>

	</nav>
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
			<div class="sidebar-sticky">
				<ul class="nav flex-column">
					<li class="nav-item"><spring:url value="/manager/add"
							var="addBook" /> <a class="nav-link active" href="${addBook}">
							<span data-feather="book"></span>Add new Book <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><spring:url value="/manager/orders"
							var="viewOrders" /><a class="nav-link" href="${viewOrders}"
						id="shopCart"> <span data-feather="inbox"></span>Orders <span
							id="Cart" class="cart">0</span>
					</a></li>

					<li class="nav-item"><spring:url value="/manager/users"
							var="listUsers" /><a class="nav-link" href="${listUsers}"> <span
							data-feather="users"></span> Users
					</a></li>

					<li class="nav-item"><spring:url value="/manager/reports"
							var="viewReports" /><a class="nav-link" href="${viewReports}">
							<span data-feather="activity"></span> Reports
					</a></li>
				</ul>

			</div>
			</nav>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			<h2>
				<center>Our Books</center>
			</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm" id="mytable">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>Title</th>
							<th>Publisher name</th>
							<th>Authors</th>
							<th>category</th>
							<th>Price</th>
							<th>available quantity</th>
							<th>Threshold</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookList }" var="book">
							<tr>
								<td>${book.isbn }</td>
								<td>${book.title }</td>
								<td>${book.publishername }</td>
								<td>${book.authors }</td>
								<td>${book.category }</td>
								<td>${book.price }</td>
								<td>${book.copiesnums }</td>
								<td>${book.threshold }</td>
								<td><spring:url value="/manager/edit"
										var="editBook" /> <a
									href="${editBook }/${book }">edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
	<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
		feather.replace()
	</script>

</body>
</html>

