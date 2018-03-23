package common.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorMessage {
	@Getter
	@Setter
	private String message;

	protected ErrorMessage() {

	}

}
