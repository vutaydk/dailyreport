<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<jsp:include page="layout/head.jsp" />
<jsp:include page="layout/nav.jsp" />
<jsp:include page="layout/sidebar-left.jsp" />
<div class="col-sm-9 offset-sm-3 col-lg-10 offset-lg-2 pt-2 main">
	<div class="card">
		<div class="card-body">
			<div class="row justify-content-center">
				<div class="col-8">
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
								<input type="button" value="Process"
									class="btn btn-outline-dark">
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-7">
					<canvas id="myChart"></canvas>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<table class="table mt-3">
						<thead>
							<tr>
								<th><m:message key="label.job.title" /></th>
								<th><m:message key="label.timeWork" /></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<tfoot>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="js/p-chart.jsp" />
<jsp:include page="layout/bottom.jsp" />