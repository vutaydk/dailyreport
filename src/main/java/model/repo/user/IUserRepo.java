package model.repo.user;

import java.util.Optional;
import model.entity.User;
import model.repo.IRepository;

public interface IUserRepo extends IRepository<User> {

	Optional<User> findByUserPassword(String em, String pwd);

	Optional<User> findById(int id);

	Optional<User> findByEmplyee(String employee);
}
