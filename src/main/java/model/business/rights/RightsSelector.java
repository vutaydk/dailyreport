package model.business.rights;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.entity.Rights;
import model.repo.right.IRightRepo;

@RequestScoped
public class RightsSelector {

	@Inject
	private IRightRepo rightsRepo;

	public List<Rights> getList() {
		return rightsRepo.getAll();
	}

	public Optional<Rights> getRightsDetailById(int id) {
		return rightsRepo.findById(id);
	}
}
