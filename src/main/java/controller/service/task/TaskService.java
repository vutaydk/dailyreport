package controller.service.task;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.business.task.AddTaskHandler;
import model.business.task.UpdateTaskHandler;
import model.entity.Task;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskService {
	@Inject
	AddTaskHandler addCommand;
	@Inject
	UpdateTaskHandler updateCommand;

	@POST
	@Path("add")
	public int insert(TaskDTO taskDTO) {
		taskDTO.isValidData();
		Task task = TaskConverter.fromDtoToEntity(taskDTO);
		// hangling data
		int taskId = addCommand.execute(task);
		return taskId;
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public int update(@PathParam("id") int id, TaskDTO taskDTO) {
		taskDTO.isValidData();
		Task task = TaskConverter.fromDtoToEntity(taskDTO);
		task.setId(id);
		// hangling data
		int taskId = updateCommand.execute(task);
		return taskId;
	}
	// @GET
	// @Path("get-all")
	// public Response getAll() {
	// log.debug("get all json");
	// Object json = TaskJson.getJson();
	// return Response.ok(json).build();
	// }
}
