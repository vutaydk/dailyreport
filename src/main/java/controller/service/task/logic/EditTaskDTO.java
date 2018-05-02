package controller.service.task.logic;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditTaskDTO {

	@Size(min = 6)
	private String name;
}
