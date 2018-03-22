package controller.report;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Project;
import model.entity.Report;
import model.entity.User;
import model.repo.ProjectRepo;
import model.repo.ReportRepo;

@WebServlet("/report")
public class ReportSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected final String VIEW_PATH = "/WEB-INF/jsps/report";

	protected ReportRepo reportDao;
	protected ProjectRepo projectDao;

	private HashMap<String, String> hashMap;

	public ReportSevlet() {
		reportDao = new ReportRepo();
		projectDao = new ProjectRepo();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("listReport", reportDao.getAll());

		// check id invalid number?
		Optional<String> detailId = Optional.ofNullable(request.getParameter("detail"));
		if (detailId.isPresent()) {

			if (DataValidation.isNumber(detailId.get())) {

				// check row report exist?
				Optional<Report> report = Optional.ofNullable(reportDao.find(Integer.valueOf(detailId.get())));
				if (report.isPresent()) {

					request.setAttribute("detailReport", report.get());
					request.setAttribute("listProject", projectDao.getAll());
				} else {

					request.setAttribute("messagePopup", "Not found data.");
				}
			}
		}

		request.getRequestDispatcher(VIEW_PATH + "/index.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check id invalid number?
		Optional<String> detailId = Optional.ofNullable(request.getParameter("detail"));
		if (detailId.isPresent()) {

			if (DataValidation.isNumber(detailId.get())) {

				// check row report exist?
				Optional<Report> opt = Optional.ofNullable(reportDao.find(Integer.valueOf(detailId.get())));
				if (opt.isPresent()) {

					// set model
					Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
					if (user.isPresent())
						opt.get().setApprover(user.get());

					opt.get().setApprovedAt(new Date(0));

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

			if (!DataValidation.isNumber(txt_projectId.get())) {
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

		Optional<String> txt_timeWorked = Optional.ofNullable(request.getParameter("txt_timeWorked"));
		if (!txt_timeWorked.isPresent() || txt_timeWorked.get().isEmpty()) {
			hashMap.put("txt_timeWorked", "Please enter working time.");
			bool = false;
		} else {

			if (!DataValidation.isNumber(txt_timeWorked.get())) {
				hashMap.put("txt_timeWorked", "Invalid working time.");
				bool = false;
			} else {

				if (Integer.valueOf(txt_timeWorked.get()) > 24) {
					hashMap.put("txt_timeWorked", "Invalid date format.");
					bool = false;
				} else {
					// try {
					// //report.setTimeWorked(new
					// SimpleDateFormat("HH").parse(txt_timeWorked.get()));
					// } catch (ParseException e) {
					// e.printStackTrace();
					// }
				}
			}
		}

		// Optional<String> txt_note =
		// Optional.ofNullable(request.getParameter("txt_note"));
		// if (!txt_note.isPresent() || txt_note.get().isEmpty()) {
		// hashMap.put("txt_note", "Please enter note.");
		// bool = false;
		// } else {
		//
		// if (txt_note.get().length() < 10) {
		// hashMap.put("txt_note", "Note length is too short (requires 10
		// characters).");
		// bool = false;
		// } else
		// report.setNote(txt_note.get());
		// }

		return bool;
	}

}
