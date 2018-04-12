package controller.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import common.util.Format;
import model.business.LoginModel;

@Path("/login")
public class LoginService {

	@POST
	@Produces("application/json")
	public String checkUser(@FormParam("txt_username") String username, @FormParam("txt_password") String password,
			@Context HttpServletRequest request) {
		System.out.println("session" + request.getAttribute("user"));

		// builder a new LoginModel
		LoginModel loginModel = LoginModel.builder().username(username).password(password).build();

		// validate form
		if (loginModel.validate()) {
			return Format.toJson("username=" + username + ",password=" + password);
		}

		return Format.toJson(loginModel.getErrorMap());
	}

}
