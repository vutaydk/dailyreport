package controller.service.rights.logic;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RightsDTO {

	@Size(min = 6)
	private String name;

	@PositiveOrZero
	private Integer level;
}
