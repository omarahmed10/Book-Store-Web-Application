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
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

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
	<span class="navbar-brand col-sm-3 col-md-2 mr-0"><spring:url
			value="/manager" var="back" /> <a class="nav-link" href="${back }">Book
			Store</a></span> </nav>
	<div class="container-fluid">
		<div class="row">

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			<h2>
				<center>Total Sales previous month</center>
			</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm" id="mytable">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>Title</th>
							<th>Date</th>
							<th>User Name</th>
							<th>Quantity</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${salesList }" var="sales">
							<tr>
								<td>${sales.bookisbn }</td>
								<td>${sales.booktitle }</td>
								<td>${sales.date }</td>
								<td>${sales.username }</td>
								<td>${sales.quantity }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<h2>
				<center>Top 5 Customers</center>
			</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm" id="mytable">
					<thead>
						<tr>
							<th>UserName</th>
							<th>Total Paid</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userList }" var="user">
							<tr>
								<td>${user.username }</td>
								<td>${user.totalpaid }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<h2>
				<center>Top 10 Books</center>
			</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm" id="mytable">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>Title</th>
							<th>Purchase Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookList }" var="book">
							<tr>
								<td>${book.isbn }</td>
								<td>${book.title }</td>
								<td>${book.totalpurchase }</td>
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

