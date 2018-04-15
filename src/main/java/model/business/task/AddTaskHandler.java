package model.business.task;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.entity.Task;
import model.repo.task.ITaskRepo;

@RequestScoped
@Transactional
public class AddTaskHandler {
	@Inject
	private ITaskRepo taskRepo;

	public int execute(Task input) {

		taskRepo.insert(input);

		return input.getId();
	}
}
