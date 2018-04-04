<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<%-- import header --%>
<jsp:include page="../layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="../layout/nav-header.jsp" />

	<div class="row">
		<%-- import sidebar --%>
		<jsp:include page="../layout/sidebar.jsp" />

		<div class="col-9">
			<div id="list-report" class="list-report">
				<div class="box">
					<div class="box-body">
						<form id="addReportForm" method="post">

							<div class="row">
								<div class="col-10">

									<div class="form-group row">
										<label for="projectCode" class="col-3 col-form-label"><m:message
												key="label.projectCode" /></label>
										<div class="col-9">
											<select id="projectCode" name="projectCode"
												class="form-control">
												<option value="">Choose...</option>
												<option value="Project1">Project 1</option>
												<option value="Project2">Project 2</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="taskCode" class="col-3 col-form-label"><m:message
												key="label.taskCode" /></label>
										<div class="col-9">
											<select id="taskCode" name="taskCode" class="form-control">
												<option value="">Choose...</option>
												<option value="Task1">Task 1</option>
												<option value="Task2">Task 2</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="timeWork" class="col-3 col-form-label"><m:message
												key="label.timeWork" /></label>
										<div class="col-9">
											<input id="timeWork" type="text" name="timeWork"
												class="form-control" placeholder="Hour">
										</div>
									</div>
								</div>

								<div class="col-2">
									<div class="row">
										<button class="btn btn-outline-danger ml-2" type="button">
											<i class="fas fa-trash-alt"></i>
										</button>
										<button id="toggle-note" class="btn btn-outline-dark  ml-2"
											type="button" data-toggle="collapse"
											data-target="#report-note" aria-controls="report-note"
											aria-expanded="false" aria-label="Toggle report note">
											<i class="fas fa-minus"></i>
										</button>
									</div>
								</div>

							</div>
							<div id="report-note">
								<div class="form-group">
									<label for="content"><m:message key="label.note" /></label>
									<div class="col-12">
										<textarea id="content" rows="10" name="note"
											class="form-control"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<button id="addWork" type="button"
											class="btn btn-outline-dark">
											<m:message key="label.submit" />
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>
</div>
<%-- import js --%>
<jsp:include page="js-add.jsp" />
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />