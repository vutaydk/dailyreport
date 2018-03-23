package controller.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/report", "/report/add" })
public class ReportSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("/report/add".equals(request.getServletPath())) {
			request.getRequestDispatcher("/WEB-INF/jsps/report/add.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/jsps/report/index.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
