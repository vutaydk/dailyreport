<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<label for="projectCode"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Project
								Code</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="projectCode" type="text" name="txt_projectCode"
									value="<c:out value="${param.txt_projectCode}"
													default="${project.projectCode}" />"
									class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<label for="name"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Name</label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="name" type="text" name="txt_name"
									value="<c:out value="${param.txt_name}"
													default="${project.name}" />"
									class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<fmt:formatDate var="startAt" value="${project.startAt}"
								pattern="dd-MM-yyyy" />
							<label for="startAt"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Start
								at </label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="startAt" type="text" name="txt_startAt"
									value="<c:out value="${param.txt_startAt}" default="${startAt}" />">
							</div>
						</div>
						<div class="form-group row">
							<fmt:formatDate var="finishAt" value="${project.finishAt}"
								pattern="dd-MM-yyyy" />
							<label for="finishAt"
								class="col-sm-4 col-md-3 col-lg-2 col-form-label">Finish
								at </label>
							<div class="col-sm-8 col-md-9 col-lg-10">
								<input id="finishAt" type="text" name="txt_finishAt"
									value="<c:out value="${param.txt_finishAt}" default="${finishAt}" />">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>
						</div>
						<script>
							var today = new Date(new Date().getFullYear(),
									new Date().getMonth(), new Date().getDate());
							$('#startAt').datepicker({
								format : 'dd-mm-yyyy',
								uiLibrary : 'bootstrap4',
								minDate : today,
								maxDate : function() {
									return $('#finishAt').val();
								}
							});
							$('#finishAt').datepicker({
								format : 'dd-mm-yyyy',
								uiLibrary : 'bootstrap4',
								minDate : function() {
									return $('#startAt').val();
								}
							});
						</script>
					</div>
				</form>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>

</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />