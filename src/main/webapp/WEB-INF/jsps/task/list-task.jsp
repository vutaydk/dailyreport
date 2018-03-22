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
		<div class="col-9" id="project-list">
			<div class="project-form">
				<div class="project-form-body">
					<!-- <div>
						<a href="task/add"><button type="button"
								class="btn btn-primary">Create task</button> </a>
					</div> -->
					<form action="task/add" method="post">
						<div class="input-group">
							<input id="btn-input" type="text" name="txt_name"
								class="form-control input-md" placeholder="Add new task" />
							<span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo">Add</button>
							</span>
						</div>
					</form>
					<table id="task-list-table" data-toggle="table" data-url="data/task" data-sort-name="id">
						<thead>
							<tr>
								<th data-field="id" class="text-center" data-sortable="true"></th>
								<th data-field="taskCode" data-sortable="true">Task Code</th>
								<th data-field="name" data-sortable="true">Name Task</th>
								<th data-field="createdAt" data-sortable="true"
									data-formatter="formatDate" data-events="actionEvents">Updated
									At</th>
								<th data-field="action" data-formatter="actionFormatter"
									data-events="actionEvents" class="text-center"></th>
							</tr>
						</thead>
					</table>
					<div id="chat"></div>
					<%-- import sub --%>
					<jsp:include page="js.jsp" />
				</div>
			</div>
			<!--/.modal detail -->
		</div>
		<!-- ./create-project form -->
	</div>
</div>
<%-- import footer --%>
<jsp:include page="../layout/footer.jsp" />