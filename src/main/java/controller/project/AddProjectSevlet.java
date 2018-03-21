package controller.project;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entity.Project;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/project/add")
public class AddProjectSevlet extends ProjectSevlet {

	private static final long serialVersionUID = 1L;

	public AddProjectSevlet() {
		super();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// redirect to project page
		response.sendRedirect(request.getContextPath() + "/project");
		return;
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Project project = new Project();
		HashMap<String, Object> map = new HashMap<>();
		Gson gson = new Gson();

		// validate form
		boolean bool = validate(request, response, project);

		// update report
		if (bool) {

			if (projectDao.insert(project))
				map.put("message", "Add success a new project.");
			else
				map.put("message", "Add error a new project.");

		} else
			map.put("error", getError());

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(gson.toJson(map));
	}

}
