<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div class="modal fade" id="project-form" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">Create
					project</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="project-form">
					<form method="post">
						<div class="project-form-body">
							<c:if test="${not empty message}">
								<div class="auth-form-message text-center">
									<p class="text-danger">
										<c:out value="${message}" />
									</p>
								</div>
							</c:if>
							<div class="form-group row">
								<label for="projectCode" class="col-3 col-form-label">Project
									code </label>
								<div class="col-9">
									<input id="projectCode" type="text" name="txt_projectCode"
										value="${param.txt_projectCode}" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label for="name" class="col-3 col-form-label">Name</label>
								<div class="col-9">
									<input id="name" type="text" name="txt_name"
										value="${param.txt_name}" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<label for="startAt" class="col-3 col-form-label">Start
									at </label>
								<div class="col-9">
									<input id="startAt" type="text" name="txt_startAt"
										value="${param.txt_startAt}">
								</div>
							</div>
							<div class="form-group row">
								<label for="finishAt" class="col-3 col-form-label">Finish
									at </label>
								<div class="col-9">
									<input id="finishAt" type="text" name="txt_finishAt"
										value="${param.txt_finishAt}">
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
										new Date().getMonth(), new Date()
												.getDate());
								$('#startAt').datepicker({
									format : 'dd-mm-yyyy',
									uiLibrary : 'bootstrap4',
									minDate : today,
									maxDate : function() {
										return $('#finishAt').val();
									}
								});
								$('#finishAt').datepicker({
									format : 'dd-mm-yyyy',
									uiLibrary : 'bootstrap4',
									minDate : function() {
										return $('#startAt').val();
									}
								});
							</script>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!--/.modal form -->