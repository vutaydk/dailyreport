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
import common.util.Shiro;
import model.business.rights.AddRightsHandler;
import model.business.rights.RightsSelector;
import model.business.rights.Role;
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
	public int insert(@Valid RightsDTO dto) {
		// check roles
		Shiro.checkRoles(Role.DIRECTOR);

		Rights rights = converter.fromDtoToEntity(dto);
		// handling data
		int rightsId = addCommand.execute(rights);
		return rightsId;
	}

	@POST
	@Path("{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public int update(@Valid RightsDTO dto, @PathParam("id") int id) {
		// check roles
		// Shiro.checkRoles(Role.DIRECTOR);

		Rights rights = converter.fromDtoToEntity(dto);
		// handling data
		int rightsId = updateCommand.execute(rights, id);
		return rightsId;
	}

	@GET
	@Path("get-all")
	public List<RightsJSON> getAll() {
		// check roles
		// Shiro.checkRoles(Role.DIRECTOR);

		return rightsSelector.getList().stream().map(r -> converter.fromEntityToJSON(r)).collect(Collectors.toList());
	}

	@GET
	@Path("get/{id: [0-9]+}")
	public RightsJSON get(@PathParam("id") int id) {
		// check roles
		// Shiro.checkRoles(Role.DIRECTOR);

		return converter.fromEntityToJSON(rightsSelector.getRightsDetailById(id).get());
	}
}
