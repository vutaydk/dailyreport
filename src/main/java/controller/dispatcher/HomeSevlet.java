package controller.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

@WebServlet("/home")
@Log4j
public class HomeSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("home page");
		request.getRequestDispatcher("WEB-INF/jsps/home.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

}