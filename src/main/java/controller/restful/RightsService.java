package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import model.business.rights.RightsDTO;
import model.business.rights.RightsLogic;

@Path("/rights")
@Produces(MediaType.APPLICATION_JSON)
public class RightsService {

	@GET
	@Path("get-all")
	public Response getAll() {
		Object entity = RightsLogic.getJson();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	public Response insert(RightsDTO rightsDTO) {

		// builder a new RightsLogic
		val val = rightsDTO.getLogic();

		// validation form
		if (val.isValidData()) {

			// add to database
			rightsDTO.insert();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, RightsDTO rightsDTO) {

		// builder a new RightsLogic
		val val = rightsDTO.getLogic();

		// check id exist
		if (!val.isValidId(id))
			return Response.status(404).build();

		// validation form
		if (val.isValidData()) {

			// update to database
			rightsDTO.update();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

}
