package controller.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j;

/**
 * Servlet implementation class LoginSevlet
 */
@WebServlet("/lang")
@Log4j
public class LanguageSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("change languge");
		String locale = (String) request.getParameter("io");
		log.debug("get attr locale=" + locale);
		// set locale to session
		request.getSession().setAttribute("locale", locale);
		response.sendRedirect(request.getContextPath() + "/");
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

}
