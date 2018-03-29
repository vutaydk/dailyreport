package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import model.business.task.TaskDTO;
import model.business.task.TaskLogic;

@Path("/task")
public class TaskService {

	@GET
	@Path("get-all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		Object entity = TaskLogic.getJson();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(TaskDTO taskDTO) {

		// builder a new TaskLogic
		val val = taskDTO.getLogic();

		// validation form
		if (val.isValidData()) {

			// add to database
			val.add();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, TaskDTO taskDTO) {

		// builder a new TaskLogic
		val val = taskDTO.getLogic();

		// check id exist
		if (!val.isValidId(id))
			return Response.status(404).build();

		// validation form
		if (val.isValidData()) {

			// update to database
			val.update();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

}
