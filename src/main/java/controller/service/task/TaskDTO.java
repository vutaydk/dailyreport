package controller.service.task;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

	@NotNull(message = "Task code must not be blank.")
	@Size(min = 4, max = 4, message = "Task code must be exactly 4 characters.")
	private String taskCode;
	@NotNull(message = "Name must not be blank.")
	@Size(min = 6, message = "Name must be at least 6 characters.")
	private String name;
}
