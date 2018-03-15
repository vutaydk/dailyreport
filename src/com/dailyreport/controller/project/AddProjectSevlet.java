package com.dailyreport.controller.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailyreport.model.Project;

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
		request.getRequestDispatcher(VIEW_PATH + "/add-project.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Project project = new Project();

		// validate form
		boolean bool = validate(request, response, project);

		// update report
		if (bool) {

			if (projectDao.insert(project)) {

				request.getSession().setAttribute("messagePopup", "Add success a new project.");
			} else {

				request.getSession().setAttribute("messagePopup", "Add error a new project.");
			}

			// redirect to report page
			response.sendRedirect(request.getContextPath() + "/project");
			return;
		} else {

			request.setAttribute("map", getError());
		}

		doGet(request, response);
	}

}
