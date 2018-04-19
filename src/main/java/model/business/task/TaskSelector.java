package model.business.task;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.entity.Task;
import model.repo.task.ITaskRepo;

@RequestScoped
public class TaskSelector {

	@Inject
	private ITaskRepo taskRepo;

	public List<Task> getList() {
		return taskRepo.getAll();
	}

	public Optional<Task> getTaskDetailById(int id) {
		return taskRepo.findById(id);
	}
}
