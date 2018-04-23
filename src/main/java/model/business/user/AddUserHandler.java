package model.business.user;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.entity.User;
import model.repo.user.IUserRepo;

@RequestScoped
public class AddUserHandler {

	@Inject
	private IUserRepo userRepo;

	@Transactional
	public int execute(User input) {

		userRepo.insert(input);

		return input.getId();
	}
}
