package model.business.user;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.Rights;
import model.entity.User;
import model.repo.right.IRightRepo;
import model.repo.user.IUserRepo;

@RequestScoped
public class AddUserHandler {

	@Inject
	private IUserRepo userRepo;
	@Inject
	private IRightRepo rightRepo;

	@Transactional
	public int execute(User input) {

		// check
		checkExistEmployeeCode(input.getEmployeeCode());
		checkExistRights(input.getRights());

		// converter
		User user = new User();
		user.setEmployeeCode(input.getEmployeeCode());
		user.setPassword(input.getPassword());
		user.setEmail(input.getEmail());
		user.setName(input.getName());
		user.setRights(input.getRights());

		// execute
		userRepo.insert(user);

		return user.getId();
	}

	private void checkExistEmployeeCode(String employee) {
		Optional<User> user = userRepo.findByEmplyee(employee);
		if (user.isPresent())
			throw new BusinessException(new RawMessage("employee code da ton tai"));
	}

	private void checkExistRights(int id) {
		Optional<Rights> rights = rightRepo.findById(id);
		if (!rights.isPresent())
			throw new BusinessException(new RawMessage("rights khong ton tai"));
	}
}
