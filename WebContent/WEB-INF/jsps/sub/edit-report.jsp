<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:if test="${not empty param.detail or not empty param.add}">
	<script type="text/javascript">
		$(window).on('load', function() {
			$('#report-modal').modal('show');
		});
	</script>
	<div class="modal fade" id="report-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Detail</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<c:if test="${not empty detailReport}">
						<div class="report-form">
							<form method="post">
								<div class="report-form-body">
									<div class="form-group row">
										<div class="col-4">
											<p>Project</p>
										</div>
										<div class="col-8">
											<p>
												<c:out value="${detailReport.project.projectCode}" />
											</p>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-4">
											<p>Worked at</p>
										</div>
										<div class="col-8">
											<p>
												<c:out value="${detailReport.workedAt}" />
											</p>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-4">
											<p>Worked to</p>
										</div>
										<div class="col-8">
											<p>
												<c:out value="${detailReport.workedTo}" />
											</p>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-4">
											<p>Note</p>
										</div>
										<div class="col-8">
											<p class="w-wrap">
												<c:out value="${detailReport.note}" />
											</p>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-4">
											<p>Approval time</p>
										</div>
										<div class="col-8">
											<p>
												<c:out value="${detailReport.approvedAt}" />
											</p>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-4">
											<p>Approver</p>
										</div>
										<div class="col-8">
											<p>
												<c:out value="${detailReport.userByApprover.name}" />
												<c:out value="${detailReport.userByApprover.rights.name}" />
											</p>
										</div>
									</div>
									<c:if test="${detailReport.approvalStatus!=1}">
										<div class="form-group row">
											<div class="col-12 text-center">
												<input type="submit" value="Approval"
													class="btn btn-primary">
											</div>
										</div>
									</c:if>
								</div>
							</form>
						</div>
					</c:if>
					<c:if test="${not empty param.add}">
						<div class="report-form">
							<form method="post">
								<div class="report-form-body">
									<c:if test="${not empty message}">
										<div class="auth-form-message text-center">
											<p class="text-danger">
												<c:out value="${message}" />
											</p>
										</div>
									</c:if>
									<div class="form-group row">
										<label for="projectCode" class="col-3 col-form-label">Project
											code</label>
										<div class="col-9">
											<select id="projectCode" name="txt_projectCode"
												class="form-control">
												<option value="">Choose...</option>
												<c:forEach items="${listProject}" var="row">
													<option value="${row.projectCode}"
														${param.txt_projectCode==row.projectCode?'selected':''}><c:out
															value="${row.name}" /></option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label for="timeWork" class="col-3 col-form-label">Time
											work</label>
										<div class="col-9">
											<input id="timeWork" type="number" name="txt_timeWork"
												value="${param.txt_timeWork}" class="form-control">
										</div>
									</div>
									<div class="form-group row">
										<label for="content" class="col-3 col-form-label">Note</label>
										<div class="col-9">
											<textarea id="content" rows="" cols="" name="txt_note"
												class="form-control">${param.txt_note}</textarea>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-12 text-center">
											<button type="submit" class="btn btn-primary">Submit</button>
											<button type="reset" class="btn btn-default">Reset</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</c:if>