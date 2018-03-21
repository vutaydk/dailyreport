<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-3">
	<div class="sidebar">
		<ul class="nav flex-column">
			<li class="nav-item p-1">
				<table id="table" data-toggle="table" data-url="data/task"
					data-search="true" data-select-item-name="toolbar1"
					data-sort-name="id" data-sort-order="desc">
					<thead>
						<tr>
							<th data-field="taskCode" data-sortable="true">Task Code</th>
							<th data-field="name" data-sortable="true">Name Task</th>
						</tr>
					</thead>
				</table>
			</li>
		</ul>
		<script>
			$('#table').bootstrapTable({
				searchTimeOut : 0,
				pageSize : 5
			});
		</script>
	</div>
</div>
<!-- ./sidebar -->