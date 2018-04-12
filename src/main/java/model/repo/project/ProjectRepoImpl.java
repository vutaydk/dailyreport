package model.repo.project;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.Project;

@RequestScoped
public class ProjectRepoImpl implements IProjectRepo {

	@Inject
	private DBConnector connector;

	@Override
	public List<Project> getAll() {
		TypedQuery<Project> query = connector.createQuery("FROM " + Project.class.getName(), Project.class);
		return query.getResultList();
	}

	@Override
	public Optional<Project> findById(int id) {
		Project project = connector.getEntityManager().find(Project.class, Integer.valueOf(id));
		return Optional.ofNullable(project);
	}

	@Override
	public boolean insert(Project project) {
		project.setCreatedAt(new Date());
		connector.insert(project);
		return true;
	}

	@Override
	public boolean update(Project project) {
		connector.update(project);
		return true;
	}

	@Override
	public boolean delete(Project project) {
		connector.delete(project);
		return true;
	}

	@Override
	public Optional<Project> getByProjectCode(String code) {
		TypedQuery<Project> query = connector.createQuery("FROM " + Project.class.getName() + " WHERE code=:code",
				Project.class);
		query.setParameter("code", code);
		return Optional.ofNullable(query.getSingleResult());
	}

}
