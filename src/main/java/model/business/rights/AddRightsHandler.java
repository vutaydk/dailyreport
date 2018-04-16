package model.business.rights;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.entity.Rights;
import model.repo.right.IRightRepo;

@RequestScoped
@Transactional
public class AddRightsHandler {
	@Inject
	private IRightRepo rightRepo;

	public int execute(Rights input) {

		rightRepo.insert(input);

		return input.getId();
	}
}
