package controller.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import common.util.Format;
import model.entity.Project;
import model.repo.ProjectRepo;

@WebServlet("/project")
public class ProjectSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected ProjectRepo projectDao;
	private HashMap<String, String> hashMap;

	public ProjectSevlet() {
		projectDao = new ProjectRepo();
		hashMap = new HashMap<>();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listProject", projectDao.getAll());
		request.getRequestDispatcher("/WEB-INF/jsps/project/list-project.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Get errors
	 * 
	 * @return HashMap
	 */
	protected HashMap<String, String> getError() {
		return hashMap;
	}

	/**
	 * Validate form
	 * 
	 * @param request
	 * @param response
	 * @param project
	 * @return boolean
	 */
	protected boolean validate(HttpServletRequest request, HttpServletResponse response, Project project) {

		boolean bool = true;
		hashMap.clear();

		// get request param
		Optional<String> txt_projectCode = Optional.ofNullable(request.getParameter("txt_projectCode"));
		if (!txt_projectCode.isPresent() || txt_projectCode.get().isEmpty()) {
			hashMap.put("txt_projectCode", "Please enter project code.");
			bool = false;
		} else {
			project.setProjectCode(txt_projectCode.get());
		}

		Optional<String> txt_name = Optional.ofNullable(request.getParameter("txt_name"));
		if (!txt_name.isPresent() || txt_name.get().isEmpty()) {
			hashMap.put("txt_name", "Please enter name.");
			bool = false;
		} else {

			if (txt_name.get().length() < 6) {
				hashMap.put("txt_name", "Name length is too short (requires 6 characters).");
				bool = false;
			} else
				project.setName(txt_name.get());
		}

		Optional<String> txt_startAt = Optional.ofNullable(request.getParameter("txt_startAt"));
		if (!txt_startAt.isPresent() || txt_startAt.get().isEmpty()) {
			hashMap.put("txt_startAt", "Please enter startAt.");
			bool = false;
		} else {

			if (!DataValidation.isValidDate(txt_startAt.get())) {
				hashMap.put("txt_startAt", "Invalid startAt.");
				bool = false;
			} else {

				Format.toDate(txt_startAt.get());
			}
		}

		Optional<String> txt_finishAt = Optional.ofNullable(request.getParameter("txt_finishAt"));
		if (!txt_finishAt.isPresent() || txt_finishAt.get().isEmpty()) {
			hashMap.put("txt_finishAt", "Please enter finishAt.");
			bool = false;
		} else {

			if (!DataValidation.isValidDate(txt_finishAt.get())) {
				hashMap.put("txt_finishAt", "Invalid startAt.");
				bool = false;
			} else {

				Format.toDate(txt_finishAt.get());
			}
		}

		return bool;
	}

}
