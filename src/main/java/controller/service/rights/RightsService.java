package controller.service.rights;

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
import controller.service.rights.logic.RightsConverter;
import controller.service.rights.logic.RightsDTO;
import controller.service.rights.logic.RightsJSON;
import model.business.rights.AddRightsHandler;
import model.business.rights.RightsSelector;
import model.business.rights.UpdateRightsHandler;
import model.entity.Rights;

@Path("/rights")
@Produces(MediaType.APPLICATION_JSON)
public class RightsService {

	@Inject
	AddRightsHandler addCommand;
	@Inject
	UpdateRightsHandler updateCommand;
	@Inject
	RightsSelector rightsSelector;
	@Inject
	RightsConverter converter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int insert(@Valid RightsDTO dto) {
		Rights rights = converter.fromAddDtoToEntity(dto);
		System.out.println(rights);
		return addCommand.execute(rights);
	}

	@POST
	@Path("{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int update(@Valid RightsDTO dto, @PathParam("id") int id) {
		Rights rights = converter.fromEditDtoToEntity(dto, id);
		System.out.println(rights);
		return updateCommand.execute(rights);
	}

	@GET
	@Path("get-all")
	@JWTTokenNeeded
	public List<RightsJSON> getAll() {
		return rightsSelector.getList().stream().map(r -> {
			return converter.fromEntityToJSON(r);
		}).collect(Collectors.toList());
	}

	@GET
	@Path("get/{id: [0-9]+}")
	@JWTTokenNeeded
	public RightsJSON get(@PathParam("id") int id) {
		Rights rights = rightsSelector.getRightsDetailById(id).get();
		return converter.fromEntityToJSON(rights);
	}
}
