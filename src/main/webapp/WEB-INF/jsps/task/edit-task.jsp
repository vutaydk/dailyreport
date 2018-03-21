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
		<jsp:include page="../layout/sidebar.jsp" />

		<div class="col-9" id="report-list">
			<div class="project-form">
				<form action="" method="post" id="addProjectForm">
					<div class="project-form-body">
						<div class="form-group row">
							<label class="col-2 col-form-label">Task code</label>
							<div class="col-4">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-2 col-form-label">Name</label>
							<div class="col-10">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12 text-center">
								<button class="btn btn-primary" type="submit">Add task</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>
</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />