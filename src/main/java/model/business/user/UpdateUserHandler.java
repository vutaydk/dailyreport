package model.business.user;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.User;
import model.repo.user.IUserRepo;

@RequestScoped
public class UpdateUserHandler {

	@Inject
	private IUserRepo userRepo;

	@Transactional
	public int execute(User input) {

		// check
		User user = checkExistId(input.getId());

		// converter
		user.setEmployeeCode(input.getEmployeeCode());
		user.setPassword(input.getPassword());
		user.setEmail(input.getEmail());
		user.setName(input.getName());
		user.setRights(input.getRights());

		// execute
		userRepo.update(user);

		return user.getId();
	}

	private User checkExistId(int id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new BusinessException(new RawMessage("user khong ton tai"));

		return user.get();
	}
}
