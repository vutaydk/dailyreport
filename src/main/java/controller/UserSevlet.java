package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import model.repo.UserRepo;

@WebServlet("/admin/user")
public class UserSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserRepo userDao;

	public UserSevlet() {
		userDao = new UserRepo();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User obUser = new User();
		obUser.setEmployeeCode("");
		obUser.setName("");
		obUser.setPassword("");
		obUser.setRights(null);
		// INSERT
		if (userDao.insert(obUser)) {
			// redirect
		}

		// UPDATE
		if (userDao.update(obUser)) {
			// redirect
		}
		doGet(request, response);
	}

}
