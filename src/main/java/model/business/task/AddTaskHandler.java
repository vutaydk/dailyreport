package model.business.task;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.Task;
import model.repo.task.ITaskRepo;

@RequestScoped
public class AddTaskHandler {

	@Inject
	private ITaskRepo taskRepo;

	@Transactional
	public int execute(Task input) {

		// check
		checkDuplicateProjectCode(input.getTaskCode());

		// converter
		Task task = new Task();
		task.setTaskCode(input.getTaskCode());
		task.setName(input.getName());

		// execute
		taskRepo.insert(task);

		return task.getId();
	}

	private void checkDuplicateProjectCode(String code) {
		Optional<Task> task = taskRepo.findByTaskCode(code);
		if (task.isPresent())
			throw new BusinessException(new RawMessage("task code da ton tai"));
	}
}
