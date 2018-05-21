package controller.service.task.logic;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddTaskDTO {

	@Size(min = 4, max = 4)
	private String taskCode;

	@Size(min = 6)
	private String name;
}
