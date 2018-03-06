<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Layout</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css">

<!-- jQuery -->
<script src="js/jquery-3.3.1.min.js"></script>

<!-- gijgo JS -->
<script src="js/gijgo.min.js" type="text/javascript"></script>

<!-- gijgo CSS -->
<link href="css/gijgo.min.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">
		        <img alt="Daily report" src="...">
		      </a>
		    </div>
		  </div>
		</nav>
		<div class="row">
			<div class="col-3">
				<ul class="nav flex-column">
				  <li class="nav-item">
				    <a class="nav-link active" href="#">Create report</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#">Link</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#">Link</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link disabled" href="#">Disabled</a>
				  </li>
				</ul>
			</div>
			<div class="col-9">
				<jsp:include page="WEB-INF/jsps/add-project.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>