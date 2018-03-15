<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- import header --%>
<jsp:include page="layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="layout/nav-header.jsp" />
	<!-- /.header -->
	<div class="row">
		<%-- import sidebar --%>
		<jsp:include page="layout/sidebar.jsp" />

		<div class="col-9" id="report-list">
			<div class="report-form">
				<form action="report/add" method="post">
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
							<label for="projectId"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Select
								Project</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<select id="projectId" name="txt_projectId" class="form-control">
									<option value="">Choose...</option>
									<c:forEach items="${listProject}" var="row">
										<option value="${row.id}"
											${param.txt_projectId==row.id?'selected':''}><c:out
												value="${row.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label for="workedAt"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Work
								At</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="workedAt" type="text" name="txt_workedAt"
									value="${param.txt_workedAt}" class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<label for="workedTo"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Work
								To</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="workedTo" type="text" name="txt_workedTo"
									value="${param.txt_workedTo}" class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<label for="note"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Note</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<textarea id="note" rows="" cols="" name="txt_note"
									class="form-control"><c:out
										value="${param.txt_note}" /></textarea>
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
<jsp:include page="layout/footer.jsp" />