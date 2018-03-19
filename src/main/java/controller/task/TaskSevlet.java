package controller.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TaskDao;
import model.entity.Task;

@WebServlet("/task")
public class TaskSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected final String VIEW_PATH = "/WEB-INF/jsps/task";

	protected TaskDao taskDao;

	private HashMap<String, String> hashMap;

	public TaskSevlet() {
		taskDao = new TaskDao();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listTask", taskDao.get());
		request.getRequestDispatcher(VIEW_PATH + "/list-task.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 * @param task
	 * @return boolean
	 */
	protected boolean validate(HttpServletRequest request, HttpServletResponse response, Task task) {

		boolean bool = true;
		hashMap = new HashMap<>();

		// get request param
//		Optional<String> txt_taskCode = Optional.ofNullable(request.getParameter("txt_taskCode"));
//		if (!txt_taskCode.isPresent() || txt_taskCode.get().isEmpty()) {
//			hashMap.put("txt_taskCode", "Please enter task code.");
//			bool = false;
//		} else {
//			if (txt_taskCode.get().length() != 4) {
//				hashMap.put("txt_taskCode", "Task code length is 4 characters.");
//				bool = false;
//			} else
//				task.setTaskCode(txt_taskCode.get());
//		}

		Optional<String> txt_name = Optional.ofNullable(request.getParameter("txt_name"));
		if (!txt_name.isPresent() || txt_name.get().isEmpty()) {
			hashMap.put("txt_name", "Please enter name.");
			bool = false;
		} else {

			if (txt_name.get().length() < 3) {
				hashMap.put("txt_name", "Name length is too short (requires 3 characters).");
				bool = false;
			} else
				task.setName(txt_name.get());
		}

		return bool;
	}

}
