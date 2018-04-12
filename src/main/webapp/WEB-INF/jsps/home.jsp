<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<jsp:include page="layout/head.jsp" />
<jsp:include page="layout/nav.jsp" />
<jsp:include page="layout/sidebar-left.jsp" />
<div class="col-sm-9 offset-sm-3 col-lg-10 offset-lg-2 pt-2 main">
	<form id="addReportForm" method="post">
		<div class="form-row mb-2">
			<div class="col-2">
				<select id="projectCode" name="projectCode" class="custom-select">
					<option value="">Choose...</option>
					<option value="qucm">Project 1</option>
					<option value="uemd">Project 2</option>
				</select>
			</div>
			<button type="button" class="btn btn-outline-dark btn-add-work">
				<m:message key="label.submit" />
			</button>
			<button id="report-submit" class="btn btn-primary " type="submit">Submit</button>
		</div>
		<div class="card mb-3">
			<div class="card-body">
				<div class="row">
					<div class="col-10">
						<div class="form-group row">
							<label class="col-3 col-form-label"> <m:message
									key="label.taskCode" />
							</label>
							<div class="col-9">
								<select name="taskCode" class="form-control">
									<option value="">Choose...</option>
									<option value="Task1">Task 1</option>
									<option value="Task2">Task 2</option>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<label class="col-3 col-form-label"> <m:message
									key="label.timeWork" />
							</label>
							<div class="col-9">
								<input type="text" name="timeWork" class="form-control"
									placeholder="Hour">
							</div>
						</div>
					</div>

					<div class="col-2">
						<div class="row justify-content-center">
							<button class="btn btn-outline-danger ml-2 btn-note-remove"
								type="button">
								<i class="fas fa-trash-alt"></i>
							</button>
							<button id="toggle-note"
								class="btn btn-outline-dark  ml-2 btn-note-toggle" type="button"
								data-toggle="collapse" data-target="#report-note"
								aria-controls="report-note" aria-expanded="false"
								aria-label="Toggle report note">
								<i class="fas fa-minus"></i>
							</button>
						</div>
					</div>

				</div>
				<div class="r-note">
					<div class="form-group">
						<label> <m:message key="label.note" />
						</label>
						<textarea rows="10" name="note" class="form-control"></textarea>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- ./create-report form -->
<jsp:include page="js/r-add.jsp" />
<jsp:include page="layout/bottom.jsp" />