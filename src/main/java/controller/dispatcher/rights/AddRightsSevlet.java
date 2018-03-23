package controller.dispatcher.rights;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Rights;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/rights/add")
public class AddRightsSevlet extends RightsSevlet {

	private static final long serialVersionUID = 1L;

	protected final String VIEW_PATH = "/WEB-INF/jsps/rights";

	public AddRightsSevlet() {
		super();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW_PATH + "/add.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Rights rights = new Rights();

		// validate form
		boolean bool = validate(request, response, rights);

		// update report
		if (bool) {

			if (rightsDao.insert(rights)) {

				request.getSession().setAttribute("messagePopup", "Add success a new project.");
			} else {

				request.getSession().setAttribute("messagePopup", "Add error a new project.");
			}

			// redirect to report page
			response.sendRedirect(request.getContextPath() + "/rights");
			return;
		} else {

			request.setAttribute("map", getError());
		}

		doGet(request, response);
	}

}
