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
	<span class="navbar-brand col-sm-3 col-md-2 mr-0"><spring:url
			value="/user" var="back" /> <a class="nav-link" href="${back }">Book
			Store</a></span> </nav>
	<div class="navbar-collapse offcanvas-collapse"
		id="navbarsExampleDefault">
		<form class="form-inline my-2 my-lg-0" action="/user/search">
			<select id="inputState" name="attribute"
				class="form-control form-control-dark mr-sm-2">
				<option selected>Title</option>
				<option>Category</option>
				<option>Author</option>
				<option>Publisher</option>
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
	<ul class="navbar-nav px-3">
		<li class="nav-item text-nowrap"><spring:url value="/user/info"
				var="account" /><a class="nav-link" href="${account }">profile</a></li>
	</ul>

	</nav>
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
			<div class="sidebar-sticky">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link" href="" id="shopCart">
							<span data-feather="shopping-cart"></span> Shopping Cart <span
							id="Cart" class="cart">0</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="#" > <span
							data-feather="user"></span> Switch to Manager mode
					</a></li>


				</ul>

			</div>
			</nav>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			<h2>
				<center>Books</center>
			</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm" id="mytable">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>Title</th>
							<th>category</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookList }" var="book">
							<tr>
								<td>${book.isbn }</td>
								<td>${book.title }</td>
								<td>${book.category }</td>
								<td>${book.price }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>

	<script>
		// little change ^-^
		var BooksToBuy = new Array();
		BooksToBuycnt = 0;
		function addRowHandlers() {

			var table = document.getElementById("mytable");
			var rows = table.getElementsByTagName("tr");
			for (i = 0; i < rows.length; i++) {
				var currentRow = table.rows[i];
				var index = i;
				var createClickHandler = function(row, index) {
					return function() {
						if (index != 0) {

							var BookId = row.getElementsByTagName("td")[0];
							var BookTitle = row.getElementsByTagName("td")[1];
							var BookPrice = row.getElementsByTagName("td")[2];
							var Id = BookId.innerHTML;
							var Title = BookTitle.innerHTML;
							var Price = BookPrice.innerHTML;
							var test = -1;
							for (var i = 0; i < BooksToBuycnt; i++) {
								if (BooksToBuy[i].id === Id) {
									test = i;
									break;
								}

							}
							if (test === -1) {
								var cnt = prompt(
										"Please enter Number of Books :", "1");
								var patt = new RegExp("^[0-9]*$");
								while (!patt.test(cnt)) {
									cnt = prompt(
											"Please enter valid Number of Books :",
											"1");
								}

								row.style.backgroundColor = "#489bf8";
								BooksToBuy[BooksToBuycnt] = {
									id : Id,
									title : Title,
									price : Price,
									num : cnt
								};
								BooksToBuycnt++;
								document.getElementById("Cart").innerHTML = BooksToBuy.length;

							} else {
								if (index % 2 === 1)
									row.style.backgroundColor = "#f3f3f2";
								else
									row.style.backgroundColor = "#ffffff";
								BooksToBuycnt--;
								BooksToBuy.splice(test, 1);
								document.getElementById("Cart").innerHTML = BooksToBuy.length;
							}
							console.log("Data : ", BooksToBuy);
						}
					}
				};
				currentRow.onclick = createClickHandler(currentRow, index);

			}
			console.log(document.getElementById(shopCart).innerHTML);
		}
	$(document).ready(function() {
     		$('#mytable').DataTable();

	      // handle request
	      $('#shopCart').on('click', function(){

		    $.ajax({
		      type: 'POST',
		      url: '/BookStory'  // write our url
		      data:{
			  arr : BooksToBuy
			},
		      dataType: json,
		      success: function(){
			  console.log("success");
			},
		      error: function(){
			console.log("error");
		      }
		    });
	      });

	 });
	window.onload = addRowHandlers();
	</script>


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

