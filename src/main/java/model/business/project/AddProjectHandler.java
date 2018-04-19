package model.business.project;

import java.util.Date;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.Project;
import model.repo.project.IProjectRepo;

@RequestScoped
@Transactional
public class AddProjectHandler {

	@Inject
	private IProjectRepo projectRepo;

	public int execute(Project input) {
		checkDuplicateProjectCode(input.getProjectCode());
		validateDateRange(input.getStartAt(), input.getFinishAt());

		projectRepo.insert(input);

		return input.getId();
	}

	private void checkDuplicateProjectCode(String code) {
		Optional<Project> project = projectRepo.findByProjectCode(code);
		if (project.isPresent())
			throw new BusinessException(new RawMessage("project code da ton tai"));
	}

	private void validateDateRange(Date startDate, Date endDate) {
		if (startDate.compareTo(endDate) > 0) {
			throw new BusinessException(new RawMessage("start date phai nho hon hoac bang end date"));
		}
	}
}
