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
	private IRightRepo rightRepo;

	public List<Rights> getAllProject() {
		return rightRepo.getAll();
	}

	public Optional<Rights> getProjectDetailById(int id) {
		return rightRepo.findById(id);
	}
}