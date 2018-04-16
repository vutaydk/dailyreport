package common.exception.message;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.spi.CDI;
import common.i18n.IResourceInitializer;
import common.i18n.SystemResourceInitializer;
import common.util.StringFormat;

public class ResourceMessage extends ErrorMessage {

	public ResourceMessage(String messageId) {
		super();
		IResourceInitializer systemResource = CDI.current().select(SystemResourceInitializer.class).get();
		String resourceMessage = systemResource.getMessage(messageId).orElse("");
		this.setMessage(resourceMessage);
	}

	public ResourceMessage(String messageId, List<String> paramsId) {
		super();
		IResourceInitializer systemResource = CDI.current().select(SystemResourceInitializer.class).get();
		List<String> params = new ArrayList<>(paramsId.size());
		paramsId.forEach(x -> {
			params.add(systemResource.getLabel(x).orElse(""));
		});
		String resourceMessage = StringFormat.formatErrMessage(systemResource.getMessage(messageId).orElse(""), params);
		this.setMessage(resourceMessage);
	}
}
