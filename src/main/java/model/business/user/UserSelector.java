package model.business.user;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.entity.User;
import model.repo.user.IUserRepo;

@RequestScoped
public class UserSelector {

	@Inject
	private IUserRepo userRepo;

	public List<User> getList() {
		return userRepo.getAll();
	}

	public Optional<User> getTaskDetailById(int id) {
		return userRepo.findById(id);
	}
}
