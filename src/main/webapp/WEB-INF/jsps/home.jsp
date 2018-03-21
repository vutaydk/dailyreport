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
						<select class="form-control">
							<option>Select project...</option>
							<option>Project 1</option>
							<option>Project 2</option>
							<option>Project 3</option>
							<option>Project 4</option>
						</select>
					</div>
					<div class="col-5">
						<select class="form-control">
							<option>Select all task...</option>
							<option>Task 1</option>
							<option>Task 2</option>
							<option>Task 3</option>
							<option>Task 4</option>
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
			var myChart = new Chart(ctx, {
				type : 'pie',
				data : {
					labels : [ "Coding", "Testing" ],
					datasets : [ {
						label : '# of Votes',
						data : [ 10, 10 ],
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
			});
		</script>
		<!-- ./add-project form -->
	</div>

</div>

<%-- import footer --%>
<jsp:include page="layout/footer.jsp" />