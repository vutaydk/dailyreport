<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Daily Report</title>

<base href="<%=request.getContextPath()%>/" />

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Font Awesome -->
<link rel="stylesheet" href="css/fontawesome-all.css" />

<!-- jQuery -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>

<!-- Bootstrap table -->
<link rel="stylesheet" href="css/bootstrap-table.css">
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-vi-VN.js"></script>

<!-- Bootstrap datepicker -->
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
<script src="js/bootstrap-datepicker.min.js"></script>

<!-- Chart -->
<script src="js/Chart.js"></script>

<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css" />
</head>

<body class="w-wrap">
	<%-- import message --%>
	<jsp:include page="message.jsp" />