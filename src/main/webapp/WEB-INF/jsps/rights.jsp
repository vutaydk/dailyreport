<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
			<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
				<jsp:include page="layout/head.jsp" />
				<jsp:include page="layout/nav.jsp" />

				<div class="col-sm-3 col-lg-2 sidebar">
					<jsp:include page="layout/sidebar-left.jsp" />
				</div>

				<div class="col-sm-9 offset-sm-3 col-lg-10 offset-lg-2 pt-2 main">
					<div class="row">
						<div class="col-md-8">
							<form id="submit-form" method="POST">
								<div class="card">
									<div class="card-body">
										<div class="form-group row">
											<label class="col-2 col-form-label">
												<m:message key="label.name" />
											</label>
											<div class="col-6">
												<input id="name" type="text" maxlength="50" name="name" class="form-control">
											</div>
										</div>

										<div class="form-group row">
											<label class="col-2 col-form-label">
												<m:message key="label.level" />
											</label>
											<div class="col-2">
												<input id="level" type="text" maxlength="2" name="level" class="form-control">
											</div>
										</div>

										<div class="form-group row">
											<div class="col-12 text-center">
												<button type="submit" class="btn btn-primary">
													<m:message key="label.submit" />
												</button>
												<button type="reset" class="btn btn-default">
													<m:message key="label.reset" />
												</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="col-md-4">
							<jsp:include page="layout/sidebar-right.jsp" />
						</div>
					</div>
				</div>
				<!-- ./create-rights form -->

				<jsp:include page="js/rights.jsp" />
				<jsp:include page="layout/bottom.jsp" />