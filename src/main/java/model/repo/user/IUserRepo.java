package model.repo.user;

import java.util.Optional;

import model.entity.User;
import model.repo.IRepository;

public interface IUserRepo extends IRepository<User> {
	Optional<User> check(String em, String pwd);
}
