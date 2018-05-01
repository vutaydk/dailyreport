package model.business.login;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.shiro.crypto.hash.Sha256Hash;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import controller.service.login.business.UserDTO;
import model.entity.User;
import model.repo.user.IUserRepo;

@RequestScoped
public class LoginHandler {

	@Inject
	IUserRepo userRepo;

	public int execute(UserDTO input) {
		checkExistEmplyee(input.getEmployeeCode());

		String hexPass = new Sha256Hash(input.getPassword()).toHex();
		Optional<User> user = userRepo.findByUserPassword(input.getEmployeeCode(), hexPass);
		if (user.isPresent())
			return user.get().getId();
		throw new BusinessException(new RawMessage("Invalid username or password"));
	}

	private void checkExistEmplyee(String employee) {
		Optional<User> user = userRepo.findByEmplyee(employee);
		if (!user.isPresent())
			throw new BusinessException(new RawMessage("Employee does not exist"));
	}
}
