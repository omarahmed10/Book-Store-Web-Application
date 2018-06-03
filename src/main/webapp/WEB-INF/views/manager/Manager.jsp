
<!doctype html>
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
<style type="text/css">
body {
	font-size: .875rem;
}

.feather {
	width: 16px;
	height: 16px;
	vertical-align: text-bottom;
}

/*
 * Sidebar
 */
.sidebar {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	z-index: 100; /* Behind the navbar */
	padding: 0;
	box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
}

.cart {
	border-radius: 10px;
	border: 3px solid #b2b2b2;
	font-size: 1.2rem;
}

.sidebar-sticky {
	position: -webkit-sticky;
	position: sticky;
	top: 48px; /* Height of navbar */
	height: calc(100vh - 48px);
	padding-top: .5rem;
	overflow-x: hidden;
	overflow-y: auto;
	/* Scrollable contents if viewport is shorter than content. */
}

.sidebar .nav-link {
	font-weight: 500;
	color: #333;
}

.sidebar .nav-link .feather {
	margin-right: 4px;
	color: #999;
}

.sidebar .nav-link.active {
	color: #007bff;
}

.sidebar .nav-link:hover .feather, .sidebar .nav-link.active .feather {
	color: inherit;
}

.sidebar-heading {
	font-size: .75rem;
	text-transform: uppercase;
}

/*
 * Navbar
 */
.navbar-brand {
	padding-top: .75rem;
	padding-bottom: .75rem;
	font-size: 1rem;
	background-color: rgba(0, 0, 0, .25);
	box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
}

.navbar .form-control {
	padding: .75rem 1rem;
	border-width: 0;
	border-radius: 0;
}

.form-control-dark {
	color: #fff;
	background-color: rgba(255, 255, 255, .1);
	border-color: rgba(255, 255, 255, .1);
}

.form-control-dark:focus {
	border-color: transparent;
	box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
}

/*
 * Utilities
 */
.border-top {
	border-top: 1px solid #e5e5e5;
}

.border-bottom {
	border-bottom: 1px solid #e5e5e5;
}
</style>

</head>

<body>
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<span class="navbar-brand col-sm-3 col-md-2 mr-0">Book Story</span>
		<div class="navbar-collapse offcanvas-collapse"
			id="navbarsExampleDefault">
			<form class="form-inline my-2 my-lg-0">
				<select id="inputState"
					class="form-control form-control-dark mr-sm-2">
					<option selected>Title</option>
					<option>Catogry</option>
					<option>Author</option>
				</select>
				<div class="col-5">
					<input class="form-control form-control-dark mr-sm-2" type="text"
						placeholder="Search" aria-label="Search">
				</div>
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>

		</div>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link" href="#">Sign
					out</a></li>
		</ul>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active" href="#">
								<span data-feather="file"></span> Products <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href=""
							id="shopCart"> <span data-feather="shopping-cart"></span>
								Shopping Cart <span id="Cart" class="cart">0</span>
						</a></li>

						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file"></span> Last Orders
						</a></li>


					</ul>

				</div>
			</nav>
			<div class="container-fluid">
				<div class="row">
					<nav class="col-md-2 d-none d-md-block bg-light sidebar">
						<div class="sidebar-sticky">
							<ul class="nav flex-column">
								<li class="nav-item"><a class="nav-link active" href="#">
										<span data-feather="home"></span> Our Products <span
										class="sr-only">(current)</span>
								</a></li>
								<li class="nav-item"><a class="nav-link" href="#"> <span
										data-feather="file"></span> Last Orders
								</a></li>

								<li class="nav-item"><a class="nav-link" href="#"> <span
										data-feather="bar-chart-2"></span> Reports
								</a></li>
							</ul>

						</div>
					</nav>

					<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
					<h2>
						<center>Our Publisher</center>
					</h2>
					<div class="table-responsive">
						<table class="table table-striped table-sm">
							<thead>
								<tr>
									<th>#</th>
									<th>Header</th>
									<th>Header</th>
									<th>Header</th>
									<th>Header</th>
								</tr>
							</thead>
							<tbody id="mytable">
								<tr>
									<td>1,001</td>
									<td>Lorem</td>
									<td>ipsum</td>
									<td>dolor</td>
									<td>sit</td>
								</tr>
								<tr>
									<td>1,002</td>
									<td>amet</td>
									<td>consectetur</td>
									<td>adipiscing</td>
									<td>elit</td>
								</tr>
								<tr>
									<td>1,003</td>
									<td>Integer</td>
									<td>nec</td>
									<td>odio</td>
									<td>Praesent</td>
								</tr>
								<tr>
									<td>1,003</td>
									<td>libero</td>
									<td>Sed</td>
									<td>cursus</td>
									<td>ante</td>
								</tr>
								<tr>
									<td>1,004</td>
									<td>dapibus</td>
									<td>diam</td>
									<td>Sed</td>
									<td>nisi</td>
								</tr>
								<tr>
									<td>1,005</td>
									<td>Nulla</td>
									<td>quis</td>
									<td>sem</td>
									<td>at</td>
								</tr>
								<tr>
									<td>1,006</td>
									<td>nibh</td>
									<td>elementum</td>
									<td>imperdiet</td>
									<td>Duis</td>
								</tr>
								<tr>
									<td>1,007</td>
									<td>sagittis</td>
									<td>ipsum</td>
									<td>Praesent</td>
									<td>mauris</td>
								</tr>
								<tr>
									<td>1,008</td>
									<td>Fusce</td>
									<td>nec</td>
									<td>tellus</td>
									<td>sed</td>
								</tr>
								<tr>
									<td>1,009</td>
									<td>augue</td>
									<td>semper</td>
									<td>porta</td>
									<td>Mauris</td>
								</tr>
								<tr>
									<td>1,010</td>
									<td>massa</td>
									<td>Vestibulum</td>
									<td>lacinia</td>
									<td>arcu</td>
								</tr>
								<tr>
									<td>1,011</td>
									<td>eget</td>
									<td>nulla</td>
									<td>Class</td>
									<td>aptent</td>
								</tr>
								<tr>
									<td>1,012</td>
									<td>taciti</td>
									<td>sociosqu</td>
									<td>ad</td>
									<td>litora</td>
								</tr>
								<tr>
									<td>1,013</td>
									<td>torquent</td>
									<td>per</td>
									<td>conubia</td>
									<td>nostra</td>
								</tr>
								<tr>
									<td>1,014</td>
									<td>per</td>
									<td>inceptos</td>
									<td>himenaeos</td>
									<td>Curabitur</td>
								</tr>
								<tr>
									<td>1,015</td>
									<td>sodales</td>
									<td>ligula</td>
									<td>in</td>
									<td>libero</td>
								</tr>
							</tbody>
						</table>
					</div>
					</main>
				</div>
			</div>

			<script>
				function addRowHandlers() {
					var table = document.getElementById("mytable");
					var rows = table.getElementsByTagName("tr");
					for (i = 0; i < rows.length; i++) {
						var currentRow = table.rows[i];
						var createClickHandler = function(row) {
							return function() {
								var cell = row.getElementsByTagName("td")[0];
								var id = cell.innerHTML;
								alert("id:" + id);
							};
						};

						currentRow.onclick = createClickHandler(currentRow);
					}
				}
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

			<!-- Graphs -->
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
			<script>
				var ctx = document.getElementById("myChart");
				var myChart = new Chart(ctx, {
					type : 'line',
					data : {
						labels : [ "Sunday", "Monday", "Tuesday", "Wednesday",
								"Thursday", "Friday", "Saturday" ],
						datasets : [ {
							data : [ 15339, 21345, 18483, 24003, 23489, 24092,
									12034 ],
							lineTension : 0,
							backgroundColor : 'transparent',
							borderColor : '#007bff',
							borderWidth : 4,
							pointBackgroundColor : '#007bff'
						} ]
					},
					options : {
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : false
								}
							} ]
						},
						legend : {
							display : false,
						}
					}
				});
			</script>
</body>
</html>

