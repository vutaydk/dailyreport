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
		<jsp:include page="sidebar.jsp" />
		<div class="col-9" id="report-list">
			<div class="box">
				<form action="rest/project/add" method="post">
					<div class="box-body">
					
						<div class="form-group row">
							<label for="projectCode" class="col-2 col-form-label">Project
								code </label>
							<div class="col-2">
								<input type="text" maxlength="4" class="form-control"
									name="txt_projectCode" value="${param.txt_projectCode}">
							</div>
						</div>
						
						<div class="form-group row">
							<label for="name" class="col-2 col-form-label">Name</label>
							<div class="col-6">
								<input type="text" maxlength="50" class="form-control"
									name="txt_name" value="${param.txt_name}">
							</div>
						</div>
						
						<div class="form-group row">
							<label for="startAt" class="col-2 col-form-label">Start
								at</label>
							<div class="col-3">
								<input id="startAt" name="txt_startAt"
									value="${param.txt_startAt}">
							</div>
							<div class="col"></div>
							<label for="finishAt" class="col-2 col-form-label">Finish
								at</label>
							<div class="col-3">
								<input id="finishAt" name="txt_finishAt"
									value="${param.txt_finishAt}">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-2 col-form-label">Project manager</label>
							<div class="col-4">
								<div class="input-group">
									<input type="text" class="form-control">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<button class="btn btn-default" type="button">
												<i class="fas fa-search"></i>
											</button>
										</div>
									</div>
								</div>
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
		<!-- ./create-project form -->
	</div>
</div>
<%-- import js --%>
<jsp:include page="js.jsp" />
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />