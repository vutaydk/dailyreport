package controller.service.task;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskService {

	// @GET
	// @Path("get-all")
	// public Response getAll() {
	// log.debug("get all json");
	// Object json = TaskJson.getJson();
	// return Response.ok(json).build();
	// }

//	@POST
//	@Path("add")
//	public int insert(TaskDTO taskDTO) {
//		taskDTO.isValidData();
//
//		return Response.ok(handling.getMessage()).build();
//	}
//
//	@POST
//	@Path("edit/{id: [0-9]+}")
//	public Response update(@PathParam("id") int id, TaskDTO taskDTO) {
//		log.debug("update task");
//		// initialize logic
//		val handling = taskDTO.getLogic();
//		// check exist object
//		if (!handling.isValidId(id))
//			return Response.status(404).build();
//		// handling data
//		if (handling.isValidData())
//			handling.update();
//		return Response.ok(handling.getMessage()).build();
//	}

}
