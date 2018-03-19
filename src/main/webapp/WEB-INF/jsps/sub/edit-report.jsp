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
											<p>Time Worked</p>
										</div>
										<div class="col-8">
											<p>
												<c:out value="${detailReport.timeWorked}" />
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
									<c:if test="${not empty detailReport.userByApprover}">
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
				</div>
			</div>
		</div>
	</div>
</c:if>