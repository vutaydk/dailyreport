package controller.service.login;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.business.login.LogoutHandler;

@Path("/logout")
@Produces(MediaType.APPLICATION_JSON)
public class LogoutService {

	@Inject
	LogoutHandler logoutHandler;

	@GET
	public int logout() {
		logoutHandler.logout();
		return 1;
	}
}
