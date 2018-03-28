package controller.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import common.util.Format;
import model.business.task.TaskDTO;
import model.business.task.TaskLogic;

@Path("/task")
public class TaskService {

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		return Format.toJson(TaskLogic.getJson());
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String insert(@FormParam("txt_taskCode") String taskCode, @FormParam("txt_name") String name) {

		// builder a new TaskLogic
		TaskLogic taskLogic = TaskDTO.builder().taskCode(taskCode).name(name).build().getLogic();

		// validation form
		if (taskLogic.isValidData()) {

			// add to database
			taskLogic.add();

			return Format.toJson(taskLogic.toString());
		}

		return Format.toJson(taskLogic.getErrorMap());
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	@Produces("application/json")
	public String update(@PathParam("id") int id, @FormParam("txt_taskCode") String taskCode,
			@FormParam("txt_name") String name) {

		// builder a new TaskLogic
		TaskLogic taskModel = TaskDTO.builder().id(id).taskCode(taskCode).name(name).build().getLogic();

		// check id exist
		if (!taskModel.isValidId())
			return "";

		// validation form
		if (taskModel.isValidData()) {

			// update to database
			taskModel.update();

			return Format.toJson(taskModel.toString());
		}

		return Format.toJson(taskModel.getErrorMap());
	}

}
