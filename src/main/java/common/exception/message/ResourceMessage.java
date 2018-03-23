package common.exception.message;

import java.util.List;

public class ResourceMessage extends ErrorMessage {
	public ResourceMessage(String messageId) {
		super();
		// TODO:getMessage from resource
		String resourceMessage = "";
		this.setMessage(resourceMessage);
	}

	public ResourceMessage(String messageId, List<String> paramsId) {
		super();
		// TODO:getMessage from resource
		String resourceMessage = "";
		this.setMessage(resourceMessage);
	}
}
