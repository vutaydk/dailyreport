<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- import header --%>
<jsp:include page="../layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="../layout/nav-header.jsp" />

	<div class="row p-3">
		<div class="col-12">
			<div class="project-form">
				<div class="project-form-body">
					<form action="" method="post">
						<div class="form-row">
							<div class="col-5">
								<div class="row">
									<label for="startAt" class="col-3 col-form-label">Start
										at</label>
									<div class="col-7">
										<input id="startAt" name="txt_startAt"
											data-validation="[DATE_DATEPICKER]">
									</div>
								</div>
							</div>
							<div class="col-5">
								<div class="row">
									<label for="finishAt" class="col-3 col-form-label">Finish
										at</label>
									<div class="col-7">
										<input id="finishAt" name="txt_finishAt"
											data-validation="[DATE_DATEPICKER]">
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
										<div class="input-group-text">
											<button class="btn btn-default" type="button">
												<i class="fas fa-user-plus"></i>
											</button>
										</div>
									</div>
									<input type="text" class="form-control"
										placeholder="Select employee">
								</div>
							</div>
						</div>
					</form>

					<table class="table mt-3">
						<thead>
							<tr>
								<th>Employee code</th>
								<th>Employee full name</th>
								<th>Task 1</th>
								<th>Task 2</th>
								<th>Task 3</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1111</td>
								<td>giaduc</td>
								<td>12</td>
								<td>3</td>
								<td>5</td>
								<td>12/02/2018</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<!-- ./add-project form -->
	</div>

</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />