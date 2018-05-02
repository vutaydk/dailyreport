package controller.service.user.logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJSON {
	private Integer id;
	private String employeeCode;
	private String email;
	private String name;
	private Integer rights;
}
