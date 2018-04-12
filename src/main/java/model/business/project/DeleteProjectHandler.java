package model.business.project;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.repo.project.IProjectRepo;

@RequestScoped
public class DeleteProjectHandler {

	@Inject
	private IProjectRepo projectRepo;

	public void execute(Integer projectId) {

	}
}
