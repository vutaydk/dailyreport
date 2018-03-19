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
				<form method="post" id="editReportForm">
					<div class="report-form-body">
						<div class="row">
							<div class="col-5">
								<div class="form-group">
									<label for="projectId">Select Project</label> <select
										id="projectId" name="txt_projectId"
										class="form-control"
										data-validation="[NOTEMPTY]">
										<option value="">Choose...</option>
										<c:forEach items="${listProject}" var="row">
											<option value="${row.id}"
												${param.txt_projectId==row.id or report.project.id==row.id?'selected':''}><c:out
													value="${row.name}" />
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<fmt:formatDate var="timeWorked" value="${report.timeWorked}"
										pattern="H" />
									<label for="timeWorked">Time Worked</label> <input id="timeWorked"
										type="text" name="txt_timeWorked"
										value="<c:out
										value="${param.txt_timeWorked}" default="${timeWorked}" />"
										class="form-control"
										data-validation="[INTEGER]" 
										data-validation-message="Please enter project code.">
								</div>
							</div>
							<div class="col-7">
								<div class="form-group">
									<label for="note">Note</label>
									<textarea id="note" rows="9" cols="" name="txt_note"
										class="form-control"
										data-validation="[L>=10, NOTEMPTY]" 
										data-validation-message="Please enter note. Not must be at least 10 characters."><c:out
											value="${param.txt_note}" default="${report.note}" />
											</textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>
						</div>
					</div>
				</form>
				<script>
					$.alterValidationRules({
						rule : 'DATE_DATEPICKER',
						regex : /^\d{2}\/\d{2}\/\d{4}$/,
						message : 'This field must use format DD/MM/YYYY to be valid.'
					});

					$.validate({
						submit : {
							settings : {
								form : '#editReportForm',
								clear : false,
								insertion : 'append',
								allErrors : true,
								errorClass : 'is-invalid',
								errorListClass : 'invalid-feedback error-list',
								inputContainer : '.form-group',
								display : 'inline',
								scrollToError : true
							},
							callback : {
								onError : function(error) {
									// alert(error.toString());
								}
							}
						}
					});
				</script>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>
</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />