package common.i18n;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("resourceBundle")
public class ResourceBundle {
	@Inject
	private IResourceInitializer systemResource;

	public String getTxt(String id) {
		return systemResource.getLabel(id).orElse(id);
	}

	public String getMsg(String id, String[] params) {
		return systemResource.getMessage(id).orElse(id);
	}

	public String getMsg(String id) {
		return systemResource.getMessage(id).orElse(id);
	}
}
