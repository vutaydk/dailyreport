package controller.task;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		request.getRequestDispatcher(VIEW_PATH + "/add.jsp").forward(request, response);
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

			taskDao.insert(task);

			// redirect to report page
			// response.sendRedirect(request.getContextPath() + "/task");
			// return;
		}

		response.setContentType("application/json;charset=UTF-8");
		Gson gson = new Gson();
		response.getWriter().append(gson.toJson(getError()));
	}

}
