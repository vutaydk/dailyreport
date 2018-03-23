package common.exception.message;

import java.util.List;

import common.util.StringFormat;

public class RawMessage extends ErrorMessage {

	public RawMessage(String message) {
		super(message);
	}

	public RawMessage(String message, List<String> params) {
		super();
		String formatedMessage = StringFormat.format(message, params);
		this.setMessage(formatedMessage);
	}
}
