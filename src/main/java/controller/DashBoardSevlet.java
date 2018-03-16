package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProjectDao;
import model.entity.Project;

@WebServlet("/home")
public class DashBoardSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProjectDao projectDao;

	public DashBoardSevlet() {
		projectDao = new ProjectDao();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Optional<List<Project>> proOptional = Optional.ofNullable(projectDao.get());
		if (proOptional.isPresent())
			request.setAttribute("listProject", proOptional.get());

		request.getRequestDispatcher("WEB-INF/jsps/home.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
