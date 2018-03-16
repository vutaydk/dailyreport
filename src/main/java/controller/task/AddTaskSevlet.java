package controller.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Task;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/task/add")
public class AddTaskSevlet extends TaskSevlet {

	private static final long serialVersionUID = 1L;

	public AddTaskSevlet() {
		super();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW_PATH + "/add-task.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Task task = new Task();

		// validate form
		boolean bool = validate(request, response, task);

		// update report
		if (bool) {

			if (taskDao.insert(task)) {

				request.getSession().setAttribute("messagePopup", "Add success a new task.");
			} else {

				request.getSession().setAttribute("messagePopup", "Add error a new task.");
			}

			// redirect to report page
			response.sendRedirect(request.getContextPath() + "/task");
			return;
		} else {

			request.setAttribute("map", getError());
		}

		doGet(request, response);
	}

}
