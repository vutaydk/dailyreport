package controller.service.rights;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RightsDTO {

	@NotNull(message = "Name must not be blank.")
	@Size(min = 6, message = "Name must be at least 6 characters.")
	private String name;
	@PositiveOrZero(message = "Level must be greater than 0.")
	private Integer level;

	public void isValidData() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<RightsDTO>> violations = validator.validate(this);
		for (ConstraintViolation<RightsDTO> constraintViolation : violations) {
			throw new BusinessException(new RawMessage(constraintViolation.getMessage()));
		}
	}
}
