package controller.service.rights;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.log4j.Log4j;

@Path("/rights")
@Produces(MediaType.APPLICATION_JSON)
@Log4j
public class RightsService {
	//
	// @GET
	// @Path("get-all")
	// public Response getAll() {
	// log.debug("get all json");
	// Object json = RightsJson.getJson();
	// return Response.ok(json).build();
	// }
	//
	// @POST
	// @Path("add")
	// public Response insert(RightsDTO rightsDTO) {
	// log.debug("insert rights");
	// // initialize logic
	// val handling = rightsDTO.getLogic();
	// // handling data
	// if (handling.isValidData())
	// handling.insert();
	// return Response.ok(handling.getMessage()).build();
	// }
	//
	// @POST
	// @Path("edit/{id: [0-9]+}")
	// public Response update(@PathParam("id") int id, RightsDTO rightsDTO) {
	// log.debug("update rights");
	// // initialize logic
	// val handling = rightsDTO.getLogic();
	// // check exist object
	// if (!handling.isValidId(id))
	// return Response.status(404).build();
	// // handling data
	// if (handling.isValidData())
	// handling.update();
	// return Response.ok(handling.getMessage()).build();
	// }

}
