<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- import header --%>
<jsp:include page="layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="layout/nav-header.jsp" />
	<!-- /.header -->

	<div class="row p-3">
		<div class="col-12">
			<form action="" method="post">
				<div class="form-row">
					<div class="col-5">
						<select id="dr_project" class="form-control">
							<option value="0">Select project...</option>
						</select>
					</div>
					<div class="col-5">
						<select id="dr_task" class="form-control">
							<option value="0">Select all task...</option>
						</select>
					</div>
					<div class="col">
						<input type="button" value="Process" class="btn btn-outline-dark">
					</div>
				</div>
			</form>

			<div class="col-12 text-center">
				<canvas id="myChart"></canvas>
			</div>
			<table class="table mt-3">
				<thead>
					<tr>
						<th>Code job title</th>
						<th>Worked time</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Coding</td>
						<td>10</td>
					</tr>
					<tr>
						<td>Testing</td>
						<td>10</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>Total</td>
						<td>20</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<script>
			// Chart
			var ctx = document.getElementById("myChart").getContext('2d');
			var data = {
				type : 'pie',
				data : {
					labels : [],
					datasets : [ {
						label : '# of Votes',
						data : [],
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)' ],
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			};
			var myChart = new Chart(ctx, data);

			// get json
			var dataJson = JSON.parse($.getJSON({
				'url' : "rest/project/get-chart",
				'async' : false
			}).responseText);

			update_chart(dataJson);

			$.each(dataJson, function(index, value) {
				$("#dr_project").append(
						'<option value="' + value.id + '">' + value.name
								+ '</option>');
			});

			$("#dr_project").change(function() {
				var selected = this.value;
				if (selected != 0)
					var projects = jQuery.grep(dataJson, function(item, i) {
						return item.id == selected;
					});
				else
					var projects = dataJson;

				update_chart(projects);
			});

			function update_chart(projects) {
				$("#dr_task").empty();
				$("#dr_task").append(
						'<option value="0">Select all task...</option>');
				var task = {};
				$.each(projects, function(i, item) {
					$.each(item.reports, function(i, item) {
						if (typeof task[item.taskId] != 'undefined') {
							var timeWorked = task[item.taskId][1]
									+ item.timeWorked;
							task[item.taskId] = [ item.taskName, timeWorked ];
						} else {
							task[item.taskId] = [ item.taskName,
									item.timeWorked ];

							$("#dr_task").append(
									'<option value="' + item.taskId + '">'
											+ item.taskName + '</option>');
						}
					});
				});

				var taskName = [], taskTime = [];
				$.each(task, function(i, item) {
					taskName.push(item[0]);
					taskTime.push(item[1]);
				});
				myChart.data.labels = taskName;
				myChart.data.datasets[0].data = taskTime;
				myChart.update();
			}
		</script>
		<!-- ./add-project form -->
	</div>

</div>

<%-- import footer --%>
<jsp:include page="layout/footer.jsp" />