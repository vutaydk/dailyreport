<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-3">
	<div class="sidebar">
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link active" href="report">Report
					Manager</a></li>
			<li class="nav-item"><a class="nav-link active" href="project">Project
					Manager</a></li>
			<li class="nav-item"><a class="nav-link active" href="rights">Rights
					Manager</a></li>
			<li class="nav-item"><a class="nav-link active" href="task">Task
					Manager</a></li>
			<li class="nav-item">
				<div class="dropdown-divider"></div>
			</li>
		</ul>
		<div class="col-12 text-center">
			<input id="datepicker" />
			<script>
				var currentDate = new Date().toLocaleDateString('en-US');
				$('#datepicker').datepicker({
					value : currentDate,
					icons: {
                        rightIcon: '<i class="far fa-calendar-alt"></i>'
                    },
				});
			</script>
		</div>
	</div>
	<table id="table1" class="table body" data-search="true">
		<thead>
			<tr>
				<th class="text-center"></th>
				<th>User</th>
				<th class="text-center"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listOwn}" var="row">
				<tr>
					<td><c:choose>
							<c:when test="${not empty row.userByApprover}">
								<i class="fas fa-check text-success"></i>
							</c:when>
							<c:otherwise>
								<i class="fas fa-times text-danger"></i>
							</c:otherwise>
						</c:choose></td>
					<%-- <td><c:out value="${fm:timeFb(row.createdAt)}" /></td> --%>
					<td><c:out value="${row.userByUserId.name}" /></td>
					<td><a href="report?detail=${row.id}"><i
							class="fas fa-search"></i></a> - <a href="report/edit?id=${row.id}"><i
							class="fas fa-edit"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		$('#table1').bootstrapTable({
			searchTimeOut : 0,
			align : 'center',
			classes : 'table table-hover',
			iconsPrefix : 'fa',
			search : true,
			icons : {
				toggle : 'fas fa-list-alt',
			},
			rowStyle : function rowStyle(row, index) {
				return {
					classes : '',
					css : {
						"background-color" : "#fff",
					}
				};
			}
		});
	</script>
</div>
<!-- ./sidebar -->