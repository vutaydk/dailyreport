<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<jsp:include page="layout/head.jsp" />
<jsp:include page="layout/nav.jsp" />
<jsp:include page="layout/sidebar-left.jsp" />
<div class="col-sm-9 offset-sm-3 col-lg-10 offset-lg-2 pt-2 main">
	<div class="card">
		<div class="card-body">
			<form action="" method="post" class="mb-3">

				<div class="form-row">
					<div class="col-5">
						<div class="row">
							<label for="startAt" class="col-3 col-form-label"> <m:message
									key="label.startAt" />
							</label>
							<div class="col-7">
								<div class="input-group">
									<input id="startAt" class="form-control"
										data-provide="datepicker" data-date-format="dd/mm/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-5">
						<div class="row">
							<label for="finishAt" class="col-3 col-form-label"> <m:message
									key="label.finishAt" />
							</label>
							<div class="col-7">
								<div class="input-group">
									<input id="finishAt" class="form-control"
										data-provide="datepicker" data-date-format="dd/mm/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col">
						<input type="button" value="Process" class="btn btn-outline-dark">
					</div>
				</div>
				<div class="form-row mt-3">
					<div class="col-4">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i
									class="fas fa-user-plus"></i>
								</span>
							</div>
							<input id="employeeSearch" type="text" class="form-control"
								placeholder="Select employee">
						</div>
						<div class="result list-group"></div>
					</div>
				</div>

			</form>
			<table id="table" data-url="api/report/get-all" data-sort-name="id">
				<thead>
					<tr>
						<th data-field="employeeCode" data-sortable="true"><m:message
								key="label.userCode" /></th>
						<th data-field="employeeName" data-sortable="true"><m:message
								key="label.user.name" /></th>
						<th>Task 1</th>
						<th>Task 2</th>
						<th>Task 3</th>
						<th data-field="date" data-sortable="true"><m:message
								key="label.date" /></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<!-- ./add-project form -->
<jsp:include page="js/report.jsp" />
<jsp:include page="layout/bottom.jsp" />