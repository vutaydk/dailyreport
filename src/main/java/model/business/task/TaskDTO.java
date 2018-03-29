package model.business.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskDTO {

	private String taskCode;
	private String name;

	public TaskLogic getLogic() {
		return new TaskLogic(this);
	}

}
