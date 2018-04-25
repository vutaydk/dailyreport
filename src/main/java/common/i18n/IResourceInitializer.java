package common.i18n;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public interface IResourceInitializer {
	ResourceDTO getResource(Locale locale);

	Map<String, String> getMessages(Locale locale);

	Map<String, String> getLabels(Locale locale);

	Optional<String> getMessage(Locale locale, String resourceId);

	Optional<String> getLabel(Locale locale, String resourceId);

	ResourceDTO getResource();

	Map<String, String> getMessages();

	Map<String, String> getLabels();

	Optional<String> getMessage(String resourceId);

	Optional<String> getLabel(String resourceId);

}
