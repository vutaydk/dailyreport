<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login page</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
		<jsp:include page="WEB-INF/jsps/header.jsp"></jsp:include>
		<div class="auth-form">
			<form action="login" class="login-form" method="post">
				<div class="auth-form-header">
					<h3>Login</h3>
				</div>
				<div class="auth-form-message">
					<%
						if (null != request.getAttribute("message")) {
					%>

					<p class="text-danger">
						<%=request.getAttribute("message")%>
					</p>

					<%
						}
					%>
				</div>
				<div class="auth-form-body">
					<div class="form-group">
						<label for="employeeCode">Employee code</label> <input
							class="form-control" type="text" id="employeeCode"
							name="employeeCode">
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input class="form-control"
							type="password" id="password" name="password">
					</div>
					<div class="form-group text-center">
						<input type="submit" value="Login" class="btn btn-primary" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>