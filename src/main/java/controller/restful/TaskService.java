package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import lombok.extern.log4j.Log4j;
import model.business.json.TaskJson;
import model.business.task.TaskDTO;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Log4j
public class TaskService {

	@GET
	@Path("get-all")
	public Response getAll() {
		Object entity = TaskJson.getJson();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	public Response insert(TaskDTO taskDTO) {
		log.debug("insert service");

		// initialize logic
		val val = taskDTO.getLogic();

		// handling data
		val.isValidData().insert();

		return Response.ok(val.getMessage()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, TaskDTO taskDTO) {

		// initialize logic
		val val = taskDTO.getLogic();

		// check exist object
		if (!val.isValidId(id))
			return Response.status(404).build();

		// handling data
		val.isValidData().update();

		return Response.ok(val.getMessage()).build();
	}

}
