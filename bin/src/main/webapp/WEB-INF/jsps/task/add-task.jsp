<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- import header --%>
<jsp:include page="../layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="../layout/nav-header.jsp" />
	<!-- /.header -->
	<div class="row">
		<%-- import sidebar --%>
		<jsp:include page="../layout/sidebar.jsp" />

		<div class="col-9" id="report-list">
			<div class="report-form">
				<form method="post">
					<div class="report-form-body">
						<c:if test="${not empty map}">
							<div class="auth-form-message text-center">
								<c:forEach items="${map}" var="m">
									<p class="text-danger">
										<c:out value="${m.value}" />
									</p>
								</c:forEach>
							</div>
						</c:if>
						<div class="form-group row">
							<label for="taskCode"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Task
								Code</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="taskCode" type="text" name="txt_taskCode"
									value="${param.txt_taskCode}" class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<label for="name"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Name
								Task</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="name" type="text" name="txt_name"
									value="${param.txt_name}" class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>

</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />