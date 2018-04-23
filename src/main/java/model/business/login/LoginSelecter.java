package model.business.login;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import model.entity.User;
import model.repo.user.IUserRepo;

@RequestScoped
public class LoginSelecter {

	@Inject
	IUserRepo userRepo;

	public Optional<User> getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		String employee = currentUser.getPrincipal().toString();
		return userRepo.findByEmplyee(employee);
	}
}
