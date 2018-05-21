package controller.service.user.logic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

	@NotBlank
	private String employeeCode;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotBlank
	private String name;

	@PositiveOrZero
	private Integer rights;
}
