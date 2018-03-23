package common.exception;

import java.util.List;

import common.exception.message.ErrorMessage;
import common.exception.message.RawMessage;
import common.exception.message.ResourceMessage;

public class BusinessException extends RuntimeException {

	private ErrorMessage message;

	public BusinessException(String messageId) {
		super();
		ResourceMessage resourceMsg = new ResourceMessage(messageId);
		this.message = resourceMsg;
	}

	public BusinessException(String messageId, List<String> parametersId) {
		super();
		ResourceMessage resourceMsg = new ResourceMessage(messageId, parametersId);
		this.message = resourceMsg;
	}

	public BusinessException(ErrorMessage errMsg) {
		this.message = errMsg;
	}

	public static BusinessException createFromRawMsg(String rawMessage) {
		RawMessage rawMsg = new RawMessage(rawMessage);
		return new BusinessException(rawMsg);
	}

	@Override
	public String getMessage() {
		return this.message.getMessage();
	}

}
