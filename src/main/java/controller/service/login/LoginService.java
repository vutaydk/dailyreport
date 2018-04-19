package controller.service.login;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import common.exception.BusinessException;
import common.exception.message.RawMessage;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {

	@POST
	public int login(@Valid UserDTO dto) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated())
			return 1;

		String hexPass = new Sha256Hash(dto.getPassword()).toHex();
		UsernamePasswordToken token = new UsernamePasswordToken(dto.getEmployeeCode(), hexPass);
		try {
			currentUser.login(token);
			return 1;
		} catch (AuthenticationException e) {
			throw new BusinessException(new RawMessage("invalid username or password"));
		}
	}
}
