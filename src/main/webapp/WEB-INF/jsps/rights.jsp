<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<%-- import header --%>
<jsp:include page="layout/header.jsp" />

<div class="container">
	<%-- import nav header --%>
	<jsp:include page="layout/nav-header.jsp" />

	<div class="row">
		<%-- import sidebar --%>
		<jsp:include page="layout/sidebar.jsp" />

		<div class="col-9" id="report-list">
			<div class="box">
				<div class="box-body">
					<form id="submit-form">

						<div class="form-group row">
							<label class="col-2 col-form-label"><m:message
									key="label.name" /></label>
							<div class="col-6">
								<input type="text" maxlength="50" name="name"
									class="form-control">
							</div>
						</div>

						<div class="form-group row">
							<label class="col-2 col-form-label"><m:message
									key="label.level" /></label>
							<div class="col-2">
								<input type="text" maxlength="2" name="level"
									class="form-control">
							</div>
						</div>

						<div class="form-group row">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">
									<m:message key="label.submit" />
								</button>
								<button type="reset" class="btn btn-default">
									<m:message key="label.submit" />
								</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
		<!-- ./create-rights form -->
	</div>
</div>
<%-- import js --%>
<jsp:include page="js/rights.jsp" />
<%-- import footer --%>
<jsp:include page="layout/footer.jsp" />