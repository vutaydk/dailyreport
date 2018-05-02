package controller.service.task;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import controller.filter.JWTTokenNeeded;
import controller.service.task.logic.AddTaskDTO;
import controller.service.task.logic.EditTaskDTO;
import controller.service.task.logic.TaskConverter;
import controller.service.task.logic.TaskJSON;
import model.business.task.AddTaskHandler;
import model.business.task.TaskSelector;
import model.business.task.UpdateTaskHandler;
import model.entity.Task;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskService {

	@Inject
	AddTaskHandler addCommand;
	@Inject
	UpdateTaskHandler updateCommand;
	@Inject
	TaskSelector taskSelector;
	@Inject
	TaskConverter converter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int insert(@Valid AddTaskDTO dto) {
		Task task = converter.fromAddDtoToEntity(dto);
		System.out.println(task);
		// handling data
		int taskId = addCommand.execute(task);
		return taskId;
	}

	@POST
	@Path("{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int update(@Valid EditTaskDTO dto, @PathParam("id") int id) {
		Task task = converter.fromEditDtoToEntity(dto, id);
		System.out.println(task);
		// handling data
		int taskId = updateCommand.execute(task);
		return taskId;
	}

	@GET
	@Path("get-all")
	@JWTTokenNeeded
	public List<TaskJSON> getAll() {
		return taskSelector.getList().stream().map(t -> {
			return converter.fromEntityToJSON(t);
		}).collect(Collectors.toList());
	}

	@GET
	@Path("get/{id: [0-9]+}")
	@JWTTokenNeeded
	public TaskJSON get(@PathParam("id") int id) {
		return converter.fromEntityToJSON(taskSelector.getTaskDetailById(id).get());
	}
}
