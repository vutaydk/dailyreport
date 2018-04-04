package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import model.business.json.RightsJson;
import model.business.rights.RightsDTO;

@Path("/rights")
@Produces(MediaType.APPLICATION_JSON)
public class RightsService {

	@GET
	@Path("get-all")
	public Response getAll() {
		Object entity = RightsJson.getJson();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	public Response insert(RightsDTO rightsDTO) {

		// initialize logic
		val val = rightsDTO.getLogic();

		// handling data
		val.isValidData().insert();

		return Response.ok(val.getMessage()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, RightsDTO rightsDTO) {

		// initialize logic
		val val = rightsDTO.getLogic();

		// check exist object
		if (!val.isValidId(id))
			return Response.status(404).build();

		// handling data
		val.isValidData().update();

		return Response.ok(val.getMessage()).build();
	}

}
