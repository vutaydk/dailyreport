package controller.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.util.DataValidation;
import model.entity.Project;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/json/project")
public class Json extends ProjectSevlet {

	private static final long serialVersionUID = 1L;

	public Json() {
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

		response.setContentType("application/json;charset=UTF-8");
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<>();
		map.put("message", "");
		map.put("error", "");

		// check id invalid number?
		Optional<String> id = Optional.ofNullable(request.getParameter("id"));
		if (id.isPresent()) {

			if (DataValidation.isNumber(id.get())) {

				// check row report exist?
				Optional<Project> project = Optional.ofNullable(projectDao.find(Integer.valueOf(id.get())));
				if (project.isPresent()) {
					map.put("error", getError());
				}
			}
		}

		response.getWriter().append(gson.toJson(map));
	}

}
