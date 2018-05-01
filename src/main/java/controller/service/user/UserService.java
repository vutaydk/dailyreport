package controller.service.user;

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
import controller.service.user.business.UserConverter;
import controller.service.user.business.UserDTO;
import controller.service.user.business.UserJSON;
import model.business.user.AddUserHandler;
import model.business.user.UpdateUserHandler;
import model.business.user.UserSelector;
import model.entity.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

	@Inject
	AddUserHandler addCommand;
	@Inject
	UpdateUserHandler updateCommand;
	@Inject
	UserSelector userSelector;
	@Inject
	UserConverter converter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int insert(@Valid UserDTO dto) {
		User user = converter.fromDtoToEntity(dto);
		// handling data
		int userId = addCommand.execute(user);
		return userId;
	}

	@POST
	@Path("{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int update(@Valid UserDTO dto, @PathParam("id") int id) {
		User user = converter.fromDtoToEntity(dto);
		// handling data
		int userId = updateCommand.execute(user, id);
		return userId;
	}

	@GET
	@Path("get-all")
	@JWTTokenNeeded
	public List<UserJSON> getAll() {
		return userSelector.getList().stream().map(u -> {
			return converter.fromEntityToJSON(u);
		}).collect(Collectors.toList());
	}
}
