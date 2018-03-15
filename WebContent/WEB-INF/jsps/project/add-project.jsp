<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
						<div class="row">
							<div class="col-6">
								<div class="form-group">
									<label for="projectCode">Project Code</label> <input
										id="projectCode" type="text" name="txt_projectCode"
										value="${param.txt_projectCode}" class="form-control">
								</div>
								<div class="form-group">
									<label for="startAt">Start at </label> <input id="startAt"
										type="text" name="txt_startAt" value="${param.txt_startAt}">
								</div>
							</div>
							<div class="col-6">
								<div class="form-group">
									<label for="name">Name</label> <input id="name" type="text"
										name="txt_name" value="${param.txt_name}" class="form-control">
								</div>

								<div class="form-group">
									<label for="finishAt">Finish at </label> <input id="finishAt"
										type="text" name="txt_finishAt" value="${param.txt_finishAt}">
								</div>
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
								minDate : today,
								icons: {
                                    rightIcon: '<i class="far fa-calendar-alt"></i>'
                                },
								maxDate : function() {
									return $('#finishAt').val();
								}
							});
							$('#finishAt').datepicker({
								format : 'dd-mm-yyyy',
								icons: {
                                    rightIcon: '<i class="far fa-calendar-alt"></i>'
                                },
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