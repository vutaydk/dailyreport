package controller.service.task.logic;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditTaskDTO {

	@Size(min = 6)
	private String name;
}
