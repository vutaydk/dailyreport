<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<jsp:include page="layout/head.jsp" />
<jsp:include page="layout/nav.jsp" />
<jsp:include page="layout/sidebar-left.jsp" />
<div class="col-sm-9 offset-sm-3 col-lg-10 offset-lg-2 pt-2 main">
	<div class="row">
		<div class="col-md-8">
			<div class="card">
				<div class="card-body">
					<form id="submit-form" action="" method="POST">

						<div class="form-group row">
							<label class="col-2 col-form-label"> <m:message
									key="label.taskCode" />
							</label>
							<div class="col-2">
								<input id="taskCode" type="text" maxlength="4" name="taskCode"
									class="form-control">
							</div>
						</div>

						<div class="form-group row">
							<label class="col-2 col-form-label"> <m:message
									key="label.name" />
							</label>
							<div class="col-6">
								<input id="name" type="text" maxlength="50" name="name"
									class="form-control">
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
		<jsp:include page="layout/sidebar-right.jsp" />
	</div>
</div>
<!-- ./create-task form -->
<jsp:include page="js/task.jsp" />
<jsp:include page="layout/bottom.jsp" />