package model.business.task;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.entity.Task;
import model.repo.task.ITaskRepo;

@RequestScoped
public class AddTaskHandler {
	@Inject
	private ITaskRepo taskRepo;

	public int execute(Task input) {

		taskRepo.insert(input);

		return input.getId();
	}
}
