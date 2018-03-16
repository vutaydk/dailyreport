<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- import header --%>
<jsp:include page="layout/header.jsp" />

<div class="container">
	<div class="auth-form">
		<form action="login" class="login-form" method="post">
			<div class="auth-form-header">
				<h3>Login</h3>
			</div>
			<div class="auth-form-body">
				<c:if test="${not empty message}">
					<div class="auth-form-message text-center">
						<p class="text-danger">
							<c:out value="${message}" />
						</p>
					</div>
				</c:if>
				<div class="form-group">
					<label for="employeeCode">Employee code</label> <input
						id="employeeCode" type="text" name="txt_username"
						value="${param.txt_username}" class="form-control">
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input id="password"
						type="password" name="txt_password" value="${param.txt_password}"
						class="form-control">
				</div>
				<div class="form-group text-center">
					<input type="submit" value="Login" class="btn btn-primary" />
				</div>
			</div>
		</form>
	</div>
	<!-- ./login form -->
</div>
<%-- import footer --%>
<jsp:include page="layout/footer.jsp" />