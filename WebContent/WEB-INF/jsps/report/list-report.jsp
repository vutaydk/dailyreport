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
			<div class="report-form">
				<div class="report-form-body">
					<div>
						<a href="report/add">
							<button type="button" class="btn btn-primary">Create
								report</button>
						</a>
					</div>
					<table id="table" data-search="true" data-pagination="true"
						class="report-table">
						<thead>
							<tr>
								<th class="text-center"></th>
								<th>User</th>
								<th>Reported</th>
								<th data-width="50%">Note</th>
								<th class="text-center"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listReport}" var="row">
								<tr>
									<td>
										<c:choose>
											<c:when test="${row.approvalStatus==1}">
												<i class="fas fa-check text-success"></i>
											</c:when>
											<c:otherwise>
												<i class="fas fa-times text-danger"></i>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:out value="${row.userByUserId.name}" />
									</td>
									<td>
										<fmt:formatDate value="${row.createdAt}" pattern="dd/MM/yyyy" />
									</td>
									<td>
										<c:out value="${row.note}" />
									</td>
									<td>
										<a href="report?detail=${row.id}">
											<i class="fas fa-search"></i>
										</a>
										-
										<a href="report/edit?id=${row.id}">
											<i class="fas fa-edit"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<script>
						$('#table').bootstrapTable({
							searchTimeOut : 0,
						});
					</script>
					<%-- import sub --%>
					<jsp:include page="../sub/edit-report.jsp" />
					<!--/.modal detail -->
				</div>
			</div>
		</div>
		<!-- ./create-report form -->
	</div>
</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />