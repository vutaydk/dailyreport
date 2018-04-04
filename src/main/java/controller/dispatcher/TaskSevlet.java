package controller.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

@WebServlet("/task")
@Log4j
public class TaskSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("task page");
		request.getRequestDispatcher("/WEB-INF/jsps/task/index.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

}
