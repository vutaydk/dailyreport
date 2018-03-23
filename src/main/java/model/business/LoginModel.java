package model.business;

import java.util.HashMap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class LoginModel {

	private String username;
	private String password;

	@Setter
	@Builder.Default
	private HashMap<String, String> errorMap = new HashMap<>();

	public boolean validate() {
		boolean bool = true;

		if (username == null || username.isEmpty()) {
			errorMap.put("txt_username", "Please enter username.");
			bool = false;
		}

		if (password == null || password.isEmpty()) {
			errorMap.put("txt_password", "Please enter password.");
			bool = false;
		}

		return bool;
	}

	public boolean push() {
		return true;
	}

}
