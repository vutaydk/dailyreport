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
	public int execute(User input, int id) {
		checkExistId(id);

		input.setId(id);
		userRepo.update(input);

		return input.getId();
	}

	private void checkExistId(int id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new BusinessException(new RawMessage("user khong ton tai"));
	}
}
