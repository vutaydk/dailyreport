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

		<div class="col-9" id="project-list">
			<div class="project-form">
				<div class="project-form-body">
					<div>
						<a href="task/add"><button type="button"
								class="btn btn-primary">Create task</button> </a>
					</div>
					<table id="table" data-url="data/task" data-search="true"
						data-pagination="true">
						<thead>
							<tr>
								<th data-field="id" class="text-center"></th>
								<th data-field="taskCode">Task Code</th>
								<th data-field="name">Name Task</th>
								<th data-field="createdAt">Updated At</th>
								<th data-field="action" data-formatter="actionFormatter"
									data-events="actionEvents" class="text-center"></th>
							</tr>
						</thead>
					</table>
					<div id="chat"> </div>
					<script>
						$('#table').bootstrapTable({
							searchTimeOut : 0,
						});
						function actionFormatter(value, row, index) {
							return [
									'<a href="rights/edit?id=' + row.id + '">',
									'<i class="fas fa-edit"></i></a>' ]
									.join('');
						}
						function rData() {
							$.ajax({
										type : 'GET',
										url : 'http://localhost:8080/daily_report/data/task',
										data : {
											get_param : 'value'
										},
										dataType : 'json',
										success : function(data) {
											$.each(data, function(index, row) {
												$('#chat').html($('<div>', {
													text : row.name
												}));
											});
										}
									});
						}
						setInterval(rData, 3000);
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