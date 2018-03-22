package controller.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;

import model.repo.ProjectRepo;

/**
 * Servlet implementation class Json
 */
@WebServlet("/data/project")
public class Json extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProjectRepo projectRepo;
	private GsonBuilder builder;

	public Json() {
		projectRepo = new ProjectRepo();
		builder = new GsonBuilder();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(builder.create().toJson(projectRepo.getAll()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
