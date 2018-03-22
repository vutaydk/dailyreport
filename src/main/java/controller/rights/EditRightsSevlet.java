package controller.rights;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Rights;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/rights/edit")
public class EditRightsSevlet extends RightsSevlet {

	private static final long serialVersionUID = 1L;

	public EditRightsSevlet() {
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
				Optional<Rights> project = Optional.ofNullable(rightsDao.find(Integer.valueOf(id.get())));
				if (project.isPresent()) {

					request.setAttribute("rights", project.get());
				} else {

					request.setAttribute("messagePopup", "Not found data.");
				}
			} else {

				request.setAttribute("messagePopup", "URL invalid.");
			}
		} else {

			request.setAttribute("messagePopup", "URL invalid.");
		}

		request.getRequestDispatcher(VIEW_PATH + "/edit.jsp").forward(request, response);
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
				Optional<Rights> rights = Optional.ofNullable(rightsDao.find(Integer.valueOf(id.get())));
				if (rights.isPresent()) {

					// validate form
					boolean bool = validate(request, response, rights.get());

					// update report
					if (bool) {

						if (rightsDao.update(rights.get())) {

							request.getSession().setAttribute("messagePopup", "Edit success current rights.");
						} else {

							request.getSession().setAttribute("messagePopup", "Edit error current rights.");
						}

						// redirect to report page
						response.sendRedirect(request.getContextPath() + "/rights");
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
