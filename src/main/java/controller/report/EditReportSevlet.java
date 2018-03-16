package controller.report;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Report;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/report/edit")
public class EditReportSevlet extends ReportSevlet {

	private static final long serialVersionUID = 1L;

	public EditReportSevlet() {
		super();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check id invalid number?
		Optional<String> id = Optional.ofNullable(request.getParameter("id"));
		if (id.isPresent()) {

			if (DataValidation.isNumber(id.get())) {

				// check row report exist?
				Optional<Report> report = Optional.ofNullable(reportDao.find(Integer.valueOf(id.get())));
				if (report.isPresent()) {

					request.setAttribute("report", report.get());
					request.setAttribute("listProject", projectDao.get());
				} else {

					request.setAttribute("messagePopup", "Not found data.");
				}
			} else {

				request.setAttribute("messagePopup", "URL invalid.");
			}
		} else {

			request.setAttribute("messagePopup", "URL invalid.");
		}

		request.getRequestDispatcher(VIEW_PATH + "/edit-report.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check id invalid number?
		Optional<String> id = Optional.ofNullable(request.getParameter("id"));
		if (id.isPresent()) {

			if (DataValidation.isNumber(id.get())) {

				// check row report exist?
				Optional<Report> report = Optional.ofNullable(reportDao.find(Integer.valueOf(id.get())));
				if (report.isPresent()) {

					// validate form
					boolean bool = validate(request, response, report.get());

					// update report
					if (bool) {

						if (reportDao.update(report.get())) {

							request.getSession().setAttribute("messagePopup", "Edit success current report.");
						} else {

							request.getSession().setAttribute("messagePopup", "Edit error current report.");
						}

						// redirect to report page
						response.sendRedirect(request.getContextPath() + "/report");
						return;
					} else {

						request.setAttribute("map", getError());
					}
				} else {

					request.setAttribute("messagePopup", "Not found data.");
				}
			}
		}

		doGet(request, response);
	}

}
