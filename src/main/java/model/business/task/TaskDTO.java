package model.business.task;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.entity.Task;
import model.repo.TaskRepo;

@Getter
@Setter
@ToString
public class TaskDTO {

	private String taskCode;
	private String name;

	private Optional<Task> task = Optional.empty();

	public TaskLogic getLogic() {
		return new TaskLogic(this);
	}

	/**
	 * Add to database
	 * 
	 * @return boolean
	 */
	public boolean insert() {
		if (!task.isPresent())
			return false;

		Task task = megerData();
		return TaskRepo.model.insert(task);
	}

	/**
	 * Update to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!task.isPresent())
			return false;

		Task task = megerData();
		return TaskRepo.model.update(task);
	}

	/**
	 * Merge data
	 * 
	 * @return Task
	 */
	private Task megerData() {
		Task task = this.task.get();
		task.setTaskCode(taskCode);
		task.setName(name);
		return task;
	}

}
