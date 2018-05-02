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
public class UpdateTaskHandler {

	@Inject
	private ITaskRepo taskRepo;

	@Transactional
	public int execute(Task input) {

		// check
		Task task = checkExistId(input.getId());
		checkDuplicateProjectCode(input.getTaskCode());

		// converter
		task.setName(input.getName());

		// execute
		taskRepo.update(task);

		return task.getId();
	}

	private Task checkExistId(int id) {
		Optional<Task> task = taskRepo.findById(id);
		if (!task.isPresent())
			throw new BusinessException(new RawMessage("task khong ton tai"));

		return task.get();
	}

	private void checkDuplicateProjectCode(String code) {
		Optional<Task> task = taskRepo.findByTaskCode(code);
		if (task.isPresent())
			throw new BusinessException(new RawMessage("task code da ton tai"));
	}
}
