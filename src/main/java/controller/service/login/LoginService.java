package controller.service.login;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import model.business.login.LoginHandler;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {

	@Inject
	LoginHandler loginHandler;

	@POST
	public int login(@Valid UserDTO dto) {
		return loginHandler.login(dto);
	}

	@GET
	public boolean getUser() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isAuthenticated();
	}

	@GET
	public boolean status() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isAuthenticated();
	}

	@GET
	@Path("{id: [0-9]+}")
	public boolean status(@PathParam("id") Integer id) {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.hasRole(id.toString());
	}
}
