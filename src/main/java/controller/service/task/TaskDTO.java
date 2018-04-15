package controller.service.task;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
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

	public void isValidData() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<TaskDTO>> violations = validator.validate(this);
		for (ConstraintViolation<TaskDTO> constraintViolation : violations) {
			throw new BusinessException(new RawMessage(constraintViolation.getMessage()));
		}
	}
}
