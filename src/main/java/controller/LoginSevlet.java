package controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import model.repo.UserRepo;

/**
 * Servlet implementation class LoginSevlet
 */
@WebServlet({ "/login", "/logout" })
public class LoginSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserRepo userDao;

	public LoginSevlet() {
		userDao = new UserRepo();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// logout success, redirect to login page
		if ("/logout".equals(request.getServletPath())) {

			// remove user session
			request.getSession().removeAttribute("user");

			// redirect to login page
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		// user session exist, redirect to home page
		Optional<User> user = Optional.ofNullable((User) request.getSession().getAttribute("user"));
		if (user.isPresent()) {

			// redirect to home page
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if ("/login".equals(request.getServletPath())) {

			// get request param
			Optional<String> em = Optional.ofNullable(request.getParameter("txt_username"));
			Optional<String> pwd = Optional.ofNullable(request.getParameter("txt_password"));

			// validate login
			if (!em.isPresent()) {
				request.setAttribute("message", "Please enter username.");
			} else if (!pwd.isPresent()) {
				request.setAttribute("message", "Please enter password.");
			} else {
				Optional<User> user = userDao.check(em.get(), pwd.get());

				// set to user session
				if (user.isPresent()) {
					request.getSession().setAttribute("user", user.get());
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				}
				request.setAttribute("message", "Invalid username or password.");
			}
		}

		doGet(request, response);
	}

}
