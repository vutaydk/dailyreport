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
import model.business.task.AddTaskHandler;
import model.business.task.TaskSelector;
import model.business.task.UpdateTaskHandler;
import model.entity.Task;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskService {
	@Inject
	AddTaskHandler addCommand;
	@Inject
	UpdateTaskHandler updateCommand;
	@Inject
	TaskSelector taskSelector;

	@POST
	@Path("add")
	public int insert(@Valid TaskDTO taskDTO) {
		Task task = TaskConverter.fromDtoToEntity(taskDTO);
		// handling data
		int taskId = addCommand.execute(task);
		return taskId;
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public int update(@Valid TaskDTO taskDTO, @PathParam("id") int id) {
		Task task = TaskConverter.fromDtoToEntity(taskDTO);
		// handling data
		int taskId = updateCommand.execute(task, id);
		return taskId;
	}

	@GET
	@Path("get-all")
	public List<TaskJSON> getAll() {
		return taskSelector.getList().stream().map(t -> TaskConverter.fromEntityToJSON(t)).collect(Collectors.toList());
	}
}
