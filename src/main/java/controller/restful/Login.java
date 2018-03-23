package controller.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import common.util.Format;
import model.business.LoginModel;

@Path("/login")
public class Login {

	@POST
	@Produces("application/json")
	public String checkUser(@FormParam("txt_username") String username, @FormParam("txt_password") String password) {

		// builder a new LoginModel
		LoginModel loginModel = LoginModel.builder().username(username).password(password).build();

		// validate form
		if (loginModel.validate()) {
			return Format.toJson("username=" + username + ",password=" + password);
		}

		return Format.toJson(loginModel.getErrorMap());
	}

}
