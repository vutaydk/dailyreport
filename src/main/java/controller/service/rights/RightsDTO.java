package controller.service.rights;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
}
