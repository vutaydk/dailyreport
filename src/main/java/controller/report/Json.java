package controller.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entity.Report;
import model.entity.TaskDetail;
import model.repo.ReportRepo;

/**
 * Servlet implementation class Json
 */
@WebServlet("/data/report")
public class Json extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ReportRepo reportRepo;
	private Gson gson;

	public Json() {
		reportRepo = new ReportRepo();
		gson = new Gson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		List<Object> object = new ArrayList<>();
		for (Report r : reportRepo.getAll()) {
			HashMap<String, Object> report = new HashMap<>();
			report.put("employeeCode", r.getUser().getEmployeeCode());
			report.put("employeeName", r.getUser().getName());
			List<Object> tasks = new ArrayList<>();
			for (TaskDetail td : r.getTaskDetails()) {
				HashMap<String, Object> task = new HashMap<>();
				task.put("code", td.getTask().getTaskCode());
				task.put("name", td.getTask().getName());
				task.put("timeWorked", td.getTimeWorked());
				task.put("note", td.getNote());
				tasks.add(task);
			}
			report.put("tasks", tasks);
			report.put("date", r.getCreatedAt());
			object.add(report);
		}
		//
		response.getWriter().append(gson.toJson(object));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
