package controller.service.login.business;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@NotNull
	private String employeeCode;

	@NotNull
	private String password;
}
