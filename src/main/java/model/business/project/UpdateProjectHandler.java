package model.business.project;

import java.util.Date;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.shiro.authz.annotation.RequiresRoles;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.business.rights.Role;
import model.entity.Project;
import model.repo.project.IProjectRepo;

@RequestScoped
@Transactional
@RequiresRoles({ Role.DIRECTOR, Role.PM })
public class UpdateProjectHandler {
	@Inject
	private IProjectRepo projectRepo;

	public int execute(Project input, int id) {
		checkExistId(id);
		checkDuplicateProjectCode(input.getProjectCode());
		validateDateRange(input.getStartAt(), input.getFinishAt());

		input.setId(id);
		projectRepo.update(input);

		return input.getId();
	}

	private void checkExistId(int id) {
		Optional<Project> project = projectRepo.findById(id);
		if (!project.isPresent())
			throw new BusinessException(new RawMessage("project khong ton tai"));
	}

	private void checkDuplicateProjectCode(String code) {
		Optional<Project> project = projectRepo.getByProjectCode(code);
		if (project.isPresent())
			throw new BusinessException(new RawMessage("project code da ton tai"));
	}

	private void validateDateRange(Date startDate, Date endDate) {
		if (startDate.compareTo(endDate) > 0) {
			throw new BusinessException(new RawMessage("start date phai nho hon hoac bang end date"));
		}
	}
}
