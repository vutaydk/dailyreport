<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<%-- import header --%>
<jsp:include page="layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="layout/nav-header.jsp" />

	<div class="row">
		<%-- import sidebar --%>
		<jsp:include page="layout/sidebar.jsp" />

		<div class="col-9" id="report-list">
			<div class="box">
				<div class="box-body">
					<form id="submit-form" action="api/project/add" method="POST">

						<div class="form-group row">
							<label for="projectCode" class="col-2 col-form-label"><m:message
									key="label.projectCode" /></label>
							<div class="col-2">
								<input id="projectCode" type="text" maxlength="4" name="projectCode"
									class="form-control">
							</div>
						</div>

						<div class="form-group row">
							<label for="name" class="col-2 col-form-label"><m:message
									key="label.name" /></label>
							<div class="col-6">
								<input id="name" type="text" maxlength="50" name="name"
									class="form-control">
							</div>
						</div>

						<div class="form-group row">
							<label for="startAt" class="col-2 col-form-label"><m:message
									key="label.startAt" /></label>
							<div class="col-3">
								<div class="input-group">
									<input id="startAt" name="startAt" class="form-control"
										data-provide="datepicker" data-date-format="dd/mm/yyyy"
										data-date-start-date="0d">
									<div class="input-group-append">
										<span class="input-group-text"><i
											class="far fa-calendar-alt"></i></span>
									</div>
								</div>
							</div>
							<div class="col"></div>
							<label for="finishAt" class="col-2 col-form-label"><m:message
									key="label.finishAt" /></label>
							<div class="col-3">
								<div class="input-group">
									<input id="finishAt" name="finishAt" class="form-control"
										data-provide="datepicker" data-date-format="dd/mm/yyyy"
										data-date-start-date="0d">
									<div class="input-group-append">
										<span class="input-group-text"><i
											class="far fa-calendar-alt"></i></span>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-2 col-form-label"><m:message
									key="label.manager" /></label>
							<div class="col-4">
								<div class="input-group">
									<input type="text" class="form-control">
									<div class="input-group-append">
										<span class="input-group-text"> <i
											class="fas fa-search"></i>
										</span>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">
									<m:message key="label.submit" />
								</button>
								<button type="reset" class="btn btn-default">
									<m:message key="label.reset" />
								</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
		<!-- ./create-project form -->
	</div>
</div>
<%-- import js --%>
<jsp:include page="js/project.jsp" />
<%-- import footer --%>
<jsp:include page="layout/footer.jsp" />