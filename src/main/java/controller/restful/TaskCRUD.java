package controller.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import common.util.Format;
import model.business.TaskModel;
import model.entity.Task;
import model.repo.TaskRepo;

@Path("/task")
public class TaskCRUD {

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		List<Object> list = new ArrayList<>();
		List<Task> tasks = TaskRepo.model.getAll();
		for (Task p : tasks) {
			HashMap<String, Object> project = new HashMap<>();
			project.put("id", p.getId());
			project.put("taskCode", p.getTaskCode());
			project.put("name", p.getName());
			list.add(project);
		}
		return Format.toJson(list);
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String insert(@FormParam("txt_projectCode") String taskCode, @FormParam("txt_name") String name) {

		// builder a new TaskModel
		TaskModel taskModel = TaskModel.builder().taskCode(taskCode).name(name).build();

		// validate form
		if (taskModel.validate())
			return Format.toJson("taskCode=" + taskCode + ",name=" + name);

		return Format.toJson(taskModel.getErrorMap());
	}

	@POST
	@Path("edit/{id: [0-9]}")
	@Produces("application/json")
	public String update(@PathParam("id") int id, @FormParam("txt_projectCode") String taskCode,
			@FormParam("txt_name") String name) {

		// check id exist
		if (TaskModel.checkId(id))
			return "";

		// builder a new TaskModel
		TaskModel taskModel = TaskModel.builder().id(id).taskCode(taskCode).name(name).build();

		// validate form
		if (taskModel.validate()) {

			// push to database
			taskModel.push();

			return Format.toJson("taskCode=" + taskCode + ",name=" + name);
		}

		return Format.toJson(taskModel.getErrorMap());
	}

	@GET
	@Path("del/{id: [0-9]}")
	@Produces("text/html")
	public String delete(@PathParam("id") int id) {

		return "id=" + id;
	}

}
