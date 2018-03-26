package model.business.task;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskEntity {

	private Integer id;
	private String taskCode;
	private String name;

	public TaskLogic getLogic() {
		return new TaskLogic(this);
	}

}
