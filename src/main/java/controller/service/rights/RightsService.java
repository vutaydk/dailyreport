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
import model.business.rights.AddRightsHandler;
import model.business.rights.RightsSelector;
import model.business.rights.UpdateRightsHandler;
import model.entity.Rights;

@Path("/rights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
	public int insert(@Valid RightsDTO dto) {
		Rights rights = converter.fromDtoToEntity(dto);
		// handling data
		int rightsId = addCommand.execute(rights);
		return rightsId;
	}

	@POST
	@Path("{id: [0-9]+}")
	public int update(@Valid RightsDTO dto, @PathParam("id") int id) {
		Rights rights = converter.fromDtoToEntity(dto);
		// handling data
		int rightsId = updateCommand.execute(rights, id);
		return rightsId;
	}

	@GET
	@Path("get-json")
	public List<RightsJSON> getJSON() {
		return rightsSelector.getList().stream().map(r -> converter.fromEntityToJSON(r)).collect(Collectors.toList());
	}
}
