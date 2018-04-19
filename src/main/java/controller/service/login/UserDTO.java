package controller.service.login;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@NotBlank
	private String employeeCode;

	@NotBlank
	private String password;
}
