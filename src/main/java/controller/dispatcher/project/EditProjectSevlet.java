package controller.dispatcher.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import common.util.Format;
import model.entity.Project;

/**
 * Servlet implementation class EditReportSevlet
 */
@WebServlet("/project/edit")
public class EditProjectSevlet extends ProjectSevlet {

	private static final long serialVersionUID = 1L;

	public EditProjectSevlet() {
		super();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		HashMap<String, Object> map = new HashMap<>();

		// check invalid id
		Optional<String> id = Optional.ofNullable(request.getParameter("id"));
		if (id.isPresent())
			if (DataValidation.isNumber(id.get())) {

				// check report row exist
				Optional<Project> project = Optional.ofNullable(projectDao.find(Integer.valueOf(id.get())));
				if (project.isPresent()) {

					// validate form
					boolean bool = validate(request, response, project.get());

					// update report
					if (bool) {

						if (projectDao.update(project.get()))
							map.put("message", "Edit success current project.");
						else
							map.put("message", "Edit error current project.");

					} else
						map.put("error", getError());
				}

				response.getWriter().append(Format.toJson(map));
				return;
			}

		response.sendError(404);
	}

}
