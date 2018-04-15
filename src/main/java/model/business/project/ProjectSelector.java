package model.business.project;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.entity.Project;
import model.repo.project.IProjectRepo;

@RequestScoped
public class ProjectSelector {
	@Inject
	private IProjectRepo projectRepo;

	public List<Project> getList() {
		return projectRepo.getAll();
	}

	public Optional<Project> getProjectDetailById(int id) {
		return projectRepo.findById(id);
	}

	public Optional<Project> getProjectDetailByCode(String code) {
		return projectRepo.getByProjectCode(code);
	}
}
