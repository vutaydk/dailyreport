<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
			<div class="report-form">
				<form>
					<div class="report-form-body">
						<div class="form-group">
							<label for="taskCode">Task Code</label> <input id="taskCode"
								type="text" name="txt_taskCode" value="${param.txt_taskCode}"
								class="form-control <c:if test="${not empty map.txt_taskCode}"><c:out value="is-invalid" /></c:if>">
							<div class="invalid-feedback">${map.txt_taskCode}</div>
						</div>
						<div class="form-group">
							<label for="name">Name Task</label> <input id="name" type="text"
								name="txt_name" value="${param.txt_name}"
								class="form-control <c:if test="${not empty map.txt_name}"><c:out value="is-invalid" /></c:if>">
							<div class="invalid-feedback">${map.txt_name}</div>
						</div>
						<div class="form-group">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="reset" class="btn btn-default">Reset</button>
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