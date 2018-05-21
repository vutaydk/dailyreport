package controller.service.login.logic;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {

	@NotNull
	private String employeeCode;

	@NotNull
	private String password;
}
