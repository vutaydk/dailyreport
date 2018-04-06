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
								<div class="col-12">
									<form id="addReportForm" method="post">
										<div id="reports" class="box">

											<div class="box-body">
												<div class="row">
													<div class="col-10">

														<div class="form-group row">
															<label class="col-3 col-form-label">
																<m:message key="label.projectCode" />
															</label>
															<div class="col-9">
																<select name="projectCode" class="form-control">
																	<option value="">Choose...</option>
																	<option value="Project1">Project 1</option>
																	<option value="Project2">Project 2</option>
																</select>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-3 col-form-label">
																<m:message key="label.taskCode" />
															</label>
															<div class="col-9">
																<select name="taskCode" class="form-control">
																	<option value="">Choose...</option>
																	<option value="Task1">Task 1</option>
																	<option value="Task2">Task 2</option>
																</select>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-3 col-form-label">
																<m:message key="label.timeWork" />
															</label>
															<div class="col-9">
																<input type="text" name="timeWork" class="form-control" placeholder="Hour">
															</div>
														</div>
													</div>

													<div class="col-2">
														<div class="row">
															<button class="btn btn-outline-danger ml-2 btn-note-remove" type="button">
																<i class="fas fa-trash-alt"></i>
															</button>
															<button id="toggle-note" class="btn btn-outline-dark  ml-2 btn-note-toggle" type="button" data-toggle="collapse" data-target="#report-note"
															    aria-controls="report-note" aria-expanded="false" aria-label="Toggle report note">
																<i class="fas fa-minus"></i>
															</button>
														</div>
													</div>

												</div>
												<div class="r-note">
													<div class="form-group">
														<label>
															<m:message key="label.note" />
														</label>
														<div class="col-12">
															<textarea rows="10" name="note" class="form-control"></textarea>
														</div>
													</div>
													<div class="row">
														<div class="col-12">
															<button type="button" class="btn btn-outline-dark btn-add-work">
																<m:message key="label.submit" />
															</button>
														</div>
													</div>
												</div>
											</div>

										</div>
										<button id="report-submit" class="btn btn-primary " type="button">Submit</button>
									</form>
									<!-- ./create-report form -->
								</div>
							</div>
					</div>
					<%-- import js --%>
						<jsp:include page="js/r-add.jsp" />
						<%-- import footer --%>
							<jsp:include page="layout/footer.jsp" />