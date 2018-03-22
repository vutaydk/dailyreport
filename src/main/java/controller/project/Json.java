package controller.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ProjectRepo;

/**
 * Servlet implementation class Json
 */
@WebServlet("/data/project")
public class Json extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProjectRepo projectRepo;
	private Gson gson;

	public Json() {
		gson = new Gson();
		projectRepo = new ProjectRepo();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(gson.toJson(projectRepo.getAll()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
