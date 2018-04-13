package controller.service.project;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

	private int id;
	@NotNull(message = "Project code must not be blank.")
	@Size(min = 4, max = 4, message = "Project code must be exactly 4 characters.")
	private String projectCode;
	@NotNull(message = "Name must not be blank.")
	@Size(min = 6, message = "Name must be at least 6 characters.")
	private String name;
	private Date startAt;
	private Date finishAt;

	public void isValidData() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(this);
		for (ConstraintViolation<ProjectDTO> constraintViolation : violations) {
			throw new BusinessException(new RawMessage(constraintViolation.getMessage()));
		}
	}
}
