package controller.project;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Project;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/project/edit")
public class EditProjectSevlet extends ProjectSevlet {

	private static final long serialVersionUID = 1L;

	public EditProjectSevlet() {
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
				Optional<Project> project = Optional.ofNullable(projectDao.find(Integer.valueOf(id.get())));
				if (project.isPresent()) {

					request.setAttribute("project", project.get());
				} else {

					request.setAttribute("messagePopup", "Not found data.");
				}
			} else {

				request.setAttribute("messagePopup", "URL invalid.");
			}
		} else {

			request.setAttribute("messagePopup", "URL invalid.");
		}

		request.getRequestDispatcher(VIEW_PATH + "/edit-project.jsp").forward(request, response);
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
				Optional<Project> project = Optional.ofNullable(projectDao.find(Integer.valueOf(id.get())));
				if (project.isPresent()) {

					// validate form
					boolean bool = validate(request, response, project.get());

					// update report
					if (bool) {

						if (projectDao.update(project.get())) {

							request.getSession().setAttribute("messagePopup", "Edit success current project.");

						} else {

							request.getSession().setAttribute("messagePopup", "Edit error current project.");
						}

						// redirect to report page
						response.sendRedirect(request.getContextPath() + "/project");
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
