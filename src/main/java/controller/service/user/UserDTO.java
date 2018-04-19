package controller.service.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
