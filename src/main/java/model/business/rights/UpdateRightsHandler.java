package model.business.rights;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.Rights;
import model.entity.User;
import model.repo.right.IRightRepo;
import model.repo.user.IUserRepo;

@RequestScoped
public class UpdateRightsHandler {

	@Inject
	private IRightRepo rightsRepo;
	@Inject
	private IUserRepo userRepo;

	@Transactional
	public int execute(Rights input, int id) {
		checkExistId(id);
		checkLevel(input.getLevel());

		input.setId(id);
		rightsRepo.update(input);

		return input.getId();
	}

	private void checkExistId(int id) {
		Optional<Rights> rights = rightsRepo.findById(id);
		if (!rights.isPresent())
			throw new BusinessException(new RawMessage("rights khong ton tai"));
	}

	private void checkLevel(int level) {
		Subject currentUser = SecurityUtils.getSubject();
		Optional<User> user = userRepo.findByEmplyee(currentUser.getPrincipal().toString());
		user.ifPresent(u -> {
			Optional<Rights> rights = rightsRepo.findById(u.getRights());
			rights.ifPresent(r -> {
				if (r.getLevel() >= level)
					return;
			});
		});

		throw new BusinessException(new RawMessage("khong du quyen"));
	}
}
