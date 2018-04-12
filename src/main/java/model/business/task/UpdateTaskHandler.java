package model.business.task;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.Task;
import model.repo.task.ITaskRepo;

@RequestScoped
public class UpdateTaskHandler {
	@Inject
	private ITaskRepo taskRepo;

	public int execute(Task input) {
		checkExistId(input.getId());

		taskRepo.update(input);

		return input.getId();
	}

	public void checkExistId(int id) {
		Optional<Task> task = taskRepo.findById(id);
		if (!task.isPresent())
			throw new BusinessException(new RawMessage("task khong ton tai"));
	}
}
