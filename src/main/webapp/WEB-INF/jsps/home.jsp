<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- import header --%>
<jsp:include page="layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="layout/nav-header.jsp" />

	<div class="box">
		<div class="box-body">
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
								<th>Code job title</th>
								<th>Worked time</th>
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
<%-- import js --%>
<jsp:include page="js.jsp" />
<%-- import footer --%>
<jsp:include page="layout/footer.jsp" />