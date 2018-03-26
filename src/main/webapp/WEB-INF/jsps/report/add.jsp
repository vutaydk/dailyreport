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
		<jsp:include page="sidebar.jsp" />
		<div class="col-9">
			<div id="list-report" class="list-report">
				<div class="box">
					<form id="addReportForm" method="post">
						<div class="box-body">
							<div class="row">
								<div class="col-10">
								
									<div class="form-group row">
										<label for="projectCode" class="col-3 col-form-label">Project
											code</label>
										<div class="col-9">
											<select id="projectCode" class="form-control"
												name="projectCode">
												<option value="">Choose...</option>
												<option value="Project1">Project 1</option>
												<option value="Project2">Project 2</option>
											</select>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="taskCode" class="col-3 col-form-label">Task
											code</label>
										<div class="col-9">
											<select id="taskCode" class="form-control" name="taskCode">
												<option value="">Choose...</option>
												<option value="Task1">Task 1</option>
												<option value="Task2">Task 2</option>
											</select>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="timeWork" class="col-3 col-form-label">Time
											work</label>
										<div class="col-9">
											<input type="text" class="form-control" id="timeWork"
												name="txt_timeWork" placeholder="Hour">
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
									<label for="content">Note</label>
									<div class="col-12">
										<textarea class="form-control" rows="10" cols="" id="content"
											name="txt_note"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<button type="button" id="addWork"
											class="btn btn-outline-dark">Add work</button>
									</div>
								</div>
							</div>
						</div>
					</form>
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