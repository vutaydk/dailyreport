package controller.task;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Task;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/task/edit")
public class EditTaskSevlet extends TaskSevlet {

	private static final long serialVersionUID = 1L;

	public EditTaskSevlet() {
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
				Optional<Task> project = Optional.ofNullable(taskDao.find(Integer.valueOf(id.get())));
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

		request.getRequestDispatcher(VIEW_PATH + "/edit-task.jsp").forward(request, response);
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
				Optional<Task> task = Optional.ofNullable(taskDao.find(Integer.valueOf(id.get())));
				if (task.isPresent()) {

					// validate form
					boolean bool = validate(request, response, task.get());

					// update report
					if (bool) {

						if (taskDao.update(task.get())) {

							request.getSession().setAttribute("messagePopup", "Edit success current task.");

						} else {

							request.getSession().setAttribute("messagePopup", "Edit error current task.");
						}

						// redirect to report page
						response.sendRedirect(request.getContextPath() + "/task");
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
