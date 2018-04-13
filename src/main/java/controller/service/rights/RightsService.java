package controller.service.rights;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.business.rights.AddRightsHandler;
import model.entity.Rights;

@Path("/rights")
@Produces(MediaType.APPLICATION_JSON)
public class RightsService {
	@Inject
	AddRightsHandler addCommand;

	@POST
	@Path("add")
	public int insert(RightsDTO rightsDTO) {
		rightsDTO.isValidData();
		Rights rights = RightsConverter.fromDtoToEntity(rightsDTO);
		// handling data
		int rightsId = addCommand.execute(rights);
		return rightsId;
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public int update(@PathParam("id") int id, RightsDTO rightsDTO) {
		rightsDTO.isValidData();
		Rights rights = RightsConverter.fromDtoToEntity(rightsDTO);
		rights.setId(id);
		// handling data
		int rightsId = addCommand.execute(rights);
		return rightsId;
	}

	// @GET
	// @Path("get-all")
	// public Response getAll() {
	// log.debug("get all json");
	// Object json = RightsJson.getJson();
	// return Response.ok(json).build();
	// }
	//
}
