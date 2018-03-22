package controller.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;

import model.repo.ReportRepo;

/**
 * Servlet implementation class Json
 */
@WebServlet("/data/report")
public class Json extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ReportRepo reportRepo;
	private GsonBuilder builder;

	public Json() {
		reportRepo = new ReportRepo();
		builder = new GsonBuilder();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(builder.create().toJson(reportRepo.getAll()));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
