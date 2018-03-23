<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-3">
	<div class="sidebar">
		<ul class="nav flex-column">
			<li class="nav-item p-1">
				<table id="table" data-toggle="table" data-url="rest/task/get-all"
					data-search="true" data-select-item-name="toolbar1"
					data-sort-name="id" data-sort-order="desc">
					<thead>
						<tr>
							<th data-field="taskCode" data-sortable="true">Task Code</th>
						</tr>
					</thead>
				</table>
			</li>
		</ul>
	</div>
</div>
<!-- ./sidebar -->