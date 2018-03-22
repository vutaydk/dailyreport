package controller.task;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.entity.Task;
import model.repo.TaskRepo;

/**
 * Servlet implementation class Json
 */
@WebServlet("/data/task")
public class Json extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Json() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Gson gson = new Gson();
		List<Task> tasks = new TaskRepo().getAll();

		response.getWriter().append(gson.toJson(tasks));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
