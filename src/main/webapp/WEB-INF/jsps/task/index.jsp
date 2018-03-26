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
			<div class="box">
				<form action="rest/task/add" method="post">
					<div class="box-body">
						<div class="form-group row">
							<label class="col-2 col-form-label">Task code</label>
							<div class="col-2">
								<input type="text" maxlength="4" class="form-control"
									name="txt_taskCode">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-2 col-form-label">Name</label>
							<div class="col-6">
								<input type="text" maxlength="50" class="form-control"
									name="txt_name">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="reset" class="btn btn-default"
									onclick="location.reload();">Reset</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- ./create-project form -->
</div>
<%-- import js --%>
<jsp:include page="js.jsp" />
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />