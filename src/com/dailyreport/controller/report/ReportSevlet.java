package com.dailyreport.controller.report;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailyreport.dao.ProjectDao;
import com.dailyreport.dao.ReportDao;
import com.dailyreport.model.Project;
import com.dailyreport.model.Report;
import com.dailyreport.model.User;
import com.dailyreport.util.Check;

@WebServlet("/report")
public class ReportSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected final String VIEW_PATH = "/WEB-INF/jsps/report";

	protected ReportDao reportDao;
	protected ProjectDao projectDao;

	private HashMap<String, String> hashMap;

	public ReportSevlet() {
		reportDao = new ReportDao();
		projectDao = new ProjectDao();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("listReport", reportDao.get());

		// check id invalid number?
		Optional<String> detailId = Optional.ofNullable(request.getParameter("detail"));
		if (detailId.isPresent()) {

			if (Check.isNumber(detailId.get())) {

				// check row report exist?
				Optional<Report> report = Optional.ofNullable(reportDao.find(Integer.valueOf(detailId.get())));
				if (report.isPresent()) {

					request.setAttribute("detailReport", report.get());
					request.setAttribute("listProject", projectDao.get());
				} else {

					request.setAttribute("messagePopup", "Not found data.");
				}
			}
		}

		request.getRequestDispatcher(VIEW_PATH + "/list-report.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check id invalid number?
		Optional<String> detailId = Optional.ofNullable(request.getParameter("detail"));
		if (detailId.isPresent()) {

			if (Check.isNumber(detailId.get())) {

				// check row report exist?
				Optional<Report> opt = Optional.ofNullable(reportDao.find(Integer.valueOf(detailId.get())));
				if (opt.isPresent()) {

					// set model
					Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
					if (user.isPresent())
						opt.get().setUserByApprover(user.get());
					opt.get().setApprovalStatus(1);
					opt.get().setApprovedAt(new Date());

					// update report
					if (reportDao.update(opt.get())) {

						request.getSession().setAttribute("messagePopup", "Approvel success.");
					} else {

						request.getSession().setAttribute("messagePopup", "Approvel error.");
					}
				}
			}
		}

		doGet(request, response);
	}

	/**
	 * Get errors
	 * 
	 * @return HashMap
	 */
	protected HashMap<String, String> getError() {
		return hashMap;
	}

	/**
	 * Validate form
	 * 
	 * @param request
	 * @param response
	 * @param report
	 * @return boolean
	 */
	protected boolean validate(HttpServletRequest request, HttpServletResponse response, Report report) {

		boolean bool = true;
		hashMap = new HashMap<>();

		// get request param
		Optional<String> txt_projectId = Optional.ofNullable(request.getParameter("txt_projectId"));
		if (!txt_projectId.isPresent() || txt_projectId.get().isEmpty()) {
			hashMap.put("txt_projectId", "Please choose project.");
			bool = false;
		} else {

			if (!Check.isNumber(txt_projectId.get())) {
				hashMap.put("txt_projectId", "Invalid URL.");
				bool = false;
			} else {

				Optional<Project> proOpt = Optional.ofNullable(projectDao.find(Integer.valueOf(txt_projectId.get())));
				if (!proOpt.isPresent()) {
					hashMap.put("txt_projectId", "Invalid project.");
					bool = false;
				} else
					report.setProject(proOpt.get());
			}
		}

		Optional<String> txt_workedAt = Optional.ofNullable(request.getParameter("txt_workedAt"));
		if (!txt_workedAt.isPresent() || txt_workedAt.get().isEmpty()) {
			hashMap.put("txt_workedAt", "Please enter working time at.");
			bool = false;
		} else {

			if (!Check.isNumber(txt_workedAt.get())) {
				hashMap.put("txt_workedAt", "Invalid working time at.");
				bool = false;
			} else {

				if (Integer.valueOf(txt_workedAt.get()) > 24) {
					hashMap.put("txt_workedAt", "Invalid date format.");
					bool = false;
				} else {
					try {
						report.setWorkedAt(new SimpleDateFormat("HH").parse(txt_workedAt.get()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}

		Optional<String> txt_workedTo = Optional.ofNullable(request.getParameter("txt_workedTo"));
		if (!txt_workedTo.isPresent() || txt_workedTo.get().isEmpty()) {
			hashMap.put("txt_workedTo", "Please enter finishing time.");
			bool = false;
		} else {

			if (!Check.isNumber(txt_workedTo.get())) {
				hashMap.put("txt_workedTo", "Invalid finishing time.");
				bool = false;
			} else {

				if (Integer.valueOf(txt_workedTo.get()) > 24) {
					hashMap.put("txt_workedTo", "Invalid date format.");
					bool = false;
				} else {

					if (Check.isNumber(txt_workedAt.get())) {

						if (Integer.valueOf(txt_workedTo.get()) < Integer.valueOf(txt_workedAt.get())) {
							hashMap.put("txt_workedTo", "Not less than the start time.");
							bool = false;
						} else {

							try {
								report.setWorkedTo(new SimpleDateFormat("HH").parse(txt_workedTo.get()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

		Optional<String> txt_note = Optional.ofNullable(request.getParameter("txt_note"));
		if (!txt_note.isPresent() || txt_note.get().isEmpty()) {
			hashMap.put("txt_note", "Please enter note.");
			bool = false;
		} else {

			if (txt_note.get().length() < 10) {
				hashMap.put("txt_note", "Note length is too short (requires 10 characters).");
				bool = false;
			} else
				report.setNote(txt_note.get());
		}

		return bool;
	}

}
