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

	@Override
	public boolean insert(Task task) {
		task.setCreatedAt(new Date());
		connector.insert(task);
		return true;
	}

	@Override
	public boolean update(Task task) {
		task.setUpdatedAt(new Date());
		connector.update(task);
		return true;
	}

	@Override
	public boolean delete(Task task) {
		connector.delete(task);
		return true;
	}

	@Override
	public Optional<Task> findById(int id) {
		Task task = connector.getEntityManager().find(Task.class, id);
		return Optional.ofNullable(task);
	}
}
