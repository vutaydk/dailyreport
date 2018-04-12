package model.repo.task;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.Task;

@RequestScoped
public class TaskRepoImpl implements ITaskRepo {

	@Inject
	private DBConnector connector;

	@Override
	public List<Task> getAll() {
		TypedQuery<Task> query = connector.createQuery("FROM " + Task.class.getName(), Task.class);
		return query.getResultList();
	}

	public Optional<Task> find(int id) {
		TypedQuery<Task> query = connector.createQuery("FROM " + Task.class.getName() + " WHERE id=:id", Task.class);
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public boolean insert(Task task) {
		task.setCreatedAt(new Date());
		connector.insert(task);
		return true;
	}

	@Override
	public boolean update(Task task) {
		connector.update(task);
		return true;
	}

	@Override
	public boolean delete(Task task) {
		connector.delete(task);
		return true;
	}

}
