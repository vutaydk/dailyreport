package model.business.rights;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.entity.Rights;
import model.repo.right.IRightRepo;

@RequestScoped
public class AddRightsHandler {
	@Inject
	private IRightRepo rightRepo;

	public int execute(Rights input) {

		rightRepo.insert(input);

		return input.getId();
	}
}
