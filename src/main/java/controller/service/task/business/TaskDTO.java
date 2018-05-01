package controller.service.task.business;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

	@Size(min = 4, max = 4)
	private String taskCode;

	@Size(min = 6)
	private String name;
}
