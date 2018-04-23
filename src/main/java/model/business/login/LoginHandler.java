package model.business.login;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import controller.service.login.UserDTO;
import model.entity.User;
import model.repo.user.IUserRepo;

@RequestScoped
public class LoginHandler {

	@Inject
	IUserRepo userRepo;

	public int login(UserDTO input) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated())
			return 1;

		checkExistEmplyee(input.getEmployeeCode());

		String hexPass = new Sha256Hash(input.getPassword()).toHex();
		UsernamePasswordToken token = new UsernamePasswordToken(input.getEmployeeCode(), hexPass);
		try {
			currentUser.login(token);
			return 1;
		} catch (AuthenticationException e) {
			throw new BusinessException(new RawMessage("Invalid username or password"));
		}
	}

	private void checkExistEmplyee(String employee) {
		Optional<User> user = userRepo.findByEmplyee(employee);
		if (!user.isPresent())
			throw new BusinessException(new RawMessage("Employee does not exist"));
	}
}
