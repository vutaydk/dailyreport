package controller.report;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Report;
import model.entity.User;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/report/add")
public class AddReportSevlet extends ReportSevlet {

	private static final long serialVersionUID = 1L;

	public AddReportSevlet() {
		super();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listProject", projectDao.getAll());
		request.getRequestDispatcher(VIEW_PATH + "/add-report.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Report report = new Report();

		// validate form
		boolean bool = validate(request, response, report);

		// update report
		if (bool) {

			Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
			if (user.isPresent())
				report.setUserByUserId(user.get());

			if (reportDao.insert(report)) {

				request.getSession().setAttribute("messagePopup", "Add success a new report.");
			} else {

				request.getSession().setAttribute("messagePopup", "Add error a new report.");
			}

			// redirect to report page
			response.sendRedirect(request.getContextPath() + "/report");
			return;
		} else {

			request.setAttribute("map", getError());
		}

		doGet(request, response);
	}

}
