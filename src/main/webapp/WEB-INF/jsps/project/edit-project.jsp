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
				<form method="post" id="editProjectForm">
					<div class="report-form-body">
						<div class="row">
							<div class="col-6">
								<div class="form-group">
									<label for="projectCode">Project Code</label>
									<input id="projectCode" type="text" name="txt_projectCode"
										value="<c:out value="${param.txt_projectCode}"
													default="${project.projectCode}" />"
										class="form-control" data-validation="[NOTEMPTY]"
										data-validation-message="Please enter project code.">
								</div>
								<div class="form-group">
									<fmt:formatDate var="startAt" value="${project.startAt}"
										pattern="dd-MM-yyyy" />
									<label for="startAt">Start at </label>
									<input id="startAt" type="text" name="txt_startAt"
										value="<c:out value="${param.txt_startAt}" default="${startAt}" />"
										data-validation="[DATE_DATEPICKER]">
								</div>
							</div>
							<div class="col-6">
								<div class="form-group">
									<label for="name">Name</label>
									<input id="name" type="text" name="txt_name"
										value="<c:out value="${param.txt_name}"
													default="${project.name}" />"
										class="form-control" data-validation="[L>=6, NOTEMPTY]"
										data-validation-message="Please enter name. Name must be at least 6 characters">
								</div>
								<div class="form-group">
									<fmt:formatDate var="finishAt" value="${project.finishAt}"
										pattern="dd-MM-yyyy" />
									<label for="finishAt">Finish at </label>
									<input id="finishAt" type="text" name="txt_finishAt"
										value="<c:out value="${param.txt_finishAt}" default="${finishAt}" />"
										data-validation="[DATE_DATEPICKER]">
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
								uiLibrary : 'bootstrap',
								format : 'dd/mm/yyyy',
								minDate : today,
								icons : {
									rightIcon : '<i class="far fa-calendar-alt"></i>'
								},
								maxDate : function() {
									return $('#finishAt').val();
								}
							});
							
							$('#finishAt').datepicker({
								uiLibrary : 'bootstrap',
								format : 'dd/mm/yyyy',
								icons : {
									rightIcon : '<i class="far fa-calendar-alt"></i>'
								},
								minDate : function() {
									return $('#startAt').val();
								}
							});

							$.alterValidationRules({
										rule : 'DATE_DATEPICKER',
										regex : /^\s*(3[01]|[12][0-9]|0?[1-9])\/(1[012]|0?[1-9])\/((?:19|20)\d{2})\s*$/,
										message : 'This field must use format DD/MM/YYYY to be valid.'
									});

							$.validate({
								submit : {
									settings : {
										form : '#editProjectForm',
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
				</form>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>
</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />