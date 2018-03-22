package controller.rights;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Rights;
import model.repo.RightsRepo;

@WebServlet("/rights")
public class RightsSevlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected final String VIEW_PATH = "/WEB-INF/jsps/rights";

	protected RightsRepo rightsDao;

	private HashMap<String, String> hashMap;

	public RightsSevlet() {
		rightsDao = new RightsRepo();
	}

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listRights", rightsDao.getAll());
		request.getRequestDispatcher(VIEW_PATH + "/list-rights.jsp").forward(request, response);
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
	 * @param rights
	 * @return boolean
	 */
	protected boolean validate(HttpServletRequest request, HttpServletResponse response, Rights rights) {

		boolean bool = true;
		hashMap = new HashMap<>();

		// get request param
		Optional<String> txt_name = Optional.ofNullable(request.getParameter("txt_name"));
		if (!txt_name.isPresent() || txt_name.get().isEmpty()) {
			hashMap.put("txt_name", "Please enter name position.");
			bool = false;
		} else {
			if (txt_name.get().length() < 3) {
				hashMap.put("txt_name", "Name position length is too short (requires 3 characters).");
				bool = false;
			} else
				rights.setName(txt_name.get());
		}

		Optional<String> txt_lv = Optional.ofNullable(request.getParameter("txt_lv"));
		if (!txt_lv.isPresent() || txt_lv.get().isEmpty()) {
			hashMap.put("txt_lv", "Please enter level.");
			bool = false;
		} else {

			if (!DataValidation.isNumber(txt_lv.get())) {
				hashMap.put("txt_lv", "Invalid level.");
				bool = false;
			} else
				rights.setLevel(Integer.valueOf(txt_lv.get()));
		}

		return bool;
	}

}
