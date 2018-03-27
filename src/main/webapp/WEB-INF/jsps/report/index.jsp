<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- import header --%>
<jsp:include page="../layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="../layout/nav-header.jsp" />

	<div class="row">
		<div class="col-12">
			<div class="box">
				<div class="box-body">
					<form action="" method="post" class="mb-3">

						<div class="form-row">
							<div class="col-5">
								<div class="row">
									<label for="startAt" class="col-3 col-form-label">Start
										at</label>
									<div class="col-7">
										<input id="startAt" name="txt_startAt">
									</div>
								</div>
							</div>

							<div class="col-5">
								<div class="row">
									<label for="finishAt" class="col-3 col-form-label">Finish
										at</label>
									<div class="col-7">
										<input id="finishAt" name="txt_finishAt">
									</div>
								</div>
							</div>

							<div class="col">
								<input type="button" value="Process"
									class="btn btn-outline-dark">
							</div>
						</div>
						<div class="form-row mt-3">
							<div class="col-4">
								<div class="input-group">
									<div class="input-group-prepend">
										<button class="btn btn-outline-secondary" type="button">
											<i class="fas fa-user-plus"></i>
										</button>
									</div>
									<input id="employeeSearch" type="text" class="form-control"
										placeholder="Select employee">
								</div>
								<div class="result list-group"></div>
							</div>
						</div>

					</form>
					<table id="table" data-url="rest/report/get-all"
						data-sort-name="id">
						<thead>
							<tr>
								<th data-field="employeeCode" data-sortable="true">Employee
									Code</th>
								<th data-field="employeeName" data-sortable="true">Employee
									Name</th>
								<th>Task 1</th>
								<th>Task 2</th>
								<th>Task 3</th>
								<th data-field="date" data-sortable="true">Date</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<!-- ./add-project form -->
	</div>
</div>
<%-- import js --%>
<jsp:include page="js.jsp" />
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />