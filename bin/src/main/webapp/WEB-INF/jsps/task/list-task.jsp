<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<div class="col-9" id="project-list">
			<div class="project-form">
				<div class="project-form-body">
					<div>
						<a href="task/add"><button type="button"
								class="btn btn-primary">Create task</button> </a>
					</div>
					<table id="table" data-search="true" data-pagination="true">
						<thead>
							<tr>
								<th class="text-center"></th>
								<th>Task Code</th>
								<th>Name Task</th>
								<th>Updated At</th>
								<th class="text-center"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listTask}" var="row" varStatus="i">
								<tr>
									<td><c:out value="${i.count}" /></td>
									<td><c:out value="${row.taskCode}" /></td>
									<td><c:out value="${row.name}" /></td>
									<td><fmt:formatDate value="${row.updatedAt}"
											pattern="dd/MM/yyyy" /></td>
									<td><a href="project/edit?id=${row.id}"><i
											class="fas fa-edit"></i></a></td>
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
					<jsp:include page="../sub/edit-project.jsp" />
				</div>
			</div>
			<!--/.modal detail -->
		</div>
		<!-- ./create-project form -->
	</div>

</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />