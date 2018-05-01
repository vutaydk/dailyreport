package controller.service.rights.business;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RightsDTO {

	@Size(min = 6)
	private String name;

	@PositiveOrZero
	private Integer level;
}
