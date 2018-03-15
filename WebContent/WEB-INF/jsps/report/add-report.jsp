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
				<form method="post">
					<div class="report-form-body">
						<div class="row">
							<div class="col-5">
								<div class="form-group">
									<label for="projectId">Select Project</label>
									<select id="projectId" name="txt_projectId"
										class="form-control <c:if test="${not empty map.txt_projectId}"><c:out value="is-invalid" /></c:if>">
										<option value="">Choose...</option>
										<c:forEach items="${listProject}" var="row">
											<option value="${row.id}"
												${param.txt_projectId==row.id?'selected':''}><c:out
													value="${row.name}" /></option>
										</c:forEach>
									</select>
									<div class="invalid-feedback">${map.txt_projectId}</div>
								</div>
								<div class="form-group">
									<label for="workedAt">Work At</label>
									<input id="workedAt" type="text" name="txt_workedAt"
										value="${param.txt_workedAt}"
										class="form-control <c:if test="${not empty map.txt_workedAt}"><c:out value="is-invalid" /></c:if>">
									<div class="invalid-feedback">${map.txt_workedAt}</div>
								</div>
								<div class="form-group">
									<label for="workedTo">Work To</label>
									<input id="workedTo" type="text" name="txt_workedTo"
										value="${param.txt_workedTo}"
										class="form-control <c:if test="${not empty map.txt_workedTo}"><c:out value="is-invalid" /></c:if>">
									<div class="invalid-feedback">${map.txt_workedTo}</div>
								</div>
							</div>
<<<<<<< HEAD
=======
						</c:if>
						<div class="row">
							<div class="col-5">
								<div class="form-group">
									<label for="projectId">Select Project</label> <select
										id="projectId" name="txt_projectId" class="form-control">
										<option value="">Choose...</option>
										<c:forEach items="${listProject}" var="row">
											<option value="${row.id}"
												${param.txt_projectId==row.id?'selected':''}><c:out
													value="${row.name}" /></option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="workedAt">Work At</label> <input id="workedAt"
										type="text" name="txt_workedAt" value="${param.txt_workedAt}"
										class="form-control">
								</div>
								<div class="form-group">
									<label for="workedTo">Work To</label> <input id="workedTo"
										type="text" name="txt_workedTo" value="${param.txt_workedTo}"
										class="form-control">
								</div>
							</div>
>>>>>>> 794030354e416a2b46052704e6f241adf2dc8a94
							<div class="col-7">
								<div class="form-group">
									<label for="note">Note</label>
									<textarea id="note" rows="9" cols="" name="txt_note"
<<<<<<< HEAD
										class="form-control <c:if test="${not empty map.txt_note}"><c:out value="is-invalid" /></c:if>"><c:out
											value="${param.txt_note}" /></textarea>
									<div class="invalid-feedback">${map.txt_note}</div>
=======
										class="form-control"><c:out
											value="${param.txt_note}" /></textarea>
>>>>>>> 794030354e416a2b46052704e6f241adf2dc8a94
								</div>
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
		</div>
		<!-- ./create-report form -->
	</div>
</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />