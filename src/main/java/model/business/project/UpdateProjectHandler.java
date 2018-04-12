package model.business.project;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import controller.service.project.ProjectDTO;
import model.repo.project.IProjectRepo;

@RequestScoped
public class UpdateProjectHandler {
	@Inject
	private IProjectRepo projectRepo;

	public ProjectDTO execute(ProjectDTO input) {
		return null;
	}
}
