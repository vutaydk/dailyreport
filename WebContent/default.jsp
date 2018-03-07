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

<!-- Font Awesome -->
<link rel="stylesheet" href="css/fontawesome-all.css">

<!-- gijgo CSS -->
<link href="css/gijgo.min.css" rel="stylesheet" type="text/css" />

<!-- jQuery -->
<script src="js/jquery-3.3.1.min.js"></script>

<!-- gijgo JS -->
<script src="js/gijgo.min.js" type="text/javascript"></script>

</head>
<body>
	<div class="container">
		<jsp:include page="WEB-INF/jsps/header.jsp"></jsp:include>
		<div class="row">
			<div class="col-3">
				<jsp:include page="WEB-INF/jsps/side-bar.jsp"></jsp:include>
			</div>
			<div class="col-9" id="report-list">
				<jsp:include page="WEB-INF/jsps/create-report.jsp"></jsp:include>
			</div>
		</div>
	</div>



	<!-- Bootstrap JS -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<!-- Custom JS -->
	<script src="js/script.js" type="text/javascript"></script>

</body>
</html>