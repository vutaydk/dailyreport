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
import common.util.Shiro;
import model.business.rights.Role;
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
	public int insert(@Valid TaskDTO dto) {
		// check roles
		Shiro.checkRoles(Role.DIRECTOR, Role.PM);

		Task task = converter.fromDtoToEntity(dto);
		// handling data
		int taskId = addCommand.execute(task);
		return taskId;
	}

	@POST
	@Path("{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public int update(@Valid TaskDTO dto, @PathParam("id") int id) {
		// check roles
		// Shiro.checkRoles(Role.DIRECTOR, Role.PM);

		Task task = converter.fromDtoToEntity(dto);
		// handling data
		int taskId = updateCommand.execute(task, id);
		return taskId;
	}

	@GET
	@Path("get-all")
	public List<TaskJSON> getAll() {
		// check roles
		// Shiro.checkRoles(Role.DIRECTOR, Role.PM);

		return taskSelector.getList().stream().map(t -> converter.fromEntityToJSON(t)).collect(Collectors.toList());
	}

	@GET
	@Path("get/{id: [0-9]+}")
	public TaskJSON get(@PathParam("id") int id) {
		// check roles
		// Shiro.checkRoles(Role.DIRECTOR, Role.PM);

		return converter.fromEntityToJSON(taskSelector.getTaskDetailById(id).get());
	}
}
