package common.i18n;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import model.entity.i18n.SystemResource;
import model.repo.i18n.ISystemResourceRepo;

@ApplicationScoped
public class SystemResourceInitializer implements IResourceInitializer {
	private Map<String, ResourceDto> resources;

	@Inject
	private ISystemResourceRepo resourceRepo;

	@PostConstruct
	public void init() {
		resources = new HashMap<>();
		List<SystemResource> allResources = resourceRepo.getAll();

		Map<String, List<SystemResource>> resourceGroupedByLocale = allResources.stream()
				.collect(Collectors.groupingBy(x -> x.getPk().getLocale()));

		Iterator<String> keyIterator = resourceGroupedByLocale.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			List<SystemResource> resourcePerLanguage = resourceGroupedByLocale.get(key);

			List<SystemResource> labelsEntity = resourcePerLanguage.stream()
					.filter(x -> x.getPk().getResourceType().equals(ResourceType.LABEL)).collect(Collectors.toList());
			Map<String, String> labels = new HashMap<>();
			labelsEntity.forEach(x -> {
				labels.put(x.getPk().getResourceCode(), x.getContent());
			});

			List<SystemResource> messagesEntity = resourcePerLanguage.stream()
					.filter(x -> x.getPk().getResourceType().equals(ResourceType.MESSAGE)).collect(Collectors.toList());
			Map<String, String> messages = new HashMap<>();
			messagesEntity.forEach(x -> {
				labels.put(x.getPk().getResourceCode(), x.getContent());
			});

			resources.put(key, new ResourceDto(messages, labels));
		}
	}

	@Override
	public ResourceDto getResource(Locale locale) {
		return resources.getOrDefault(locale.getLanguage(), new ResourceDto());
	}

	@Override
	public Map<String, String> getMessages(Locale locale) {
		return getResource(locale).getMessages();
	}

	@Override
	public Map<String, String> getLabels(Locale locale) {
		return getResource(locale).getLabels();
	}

	@Override
	public Optional<String> getMessage(Locale locale, String resourceId) {
		return Optional.ofNullable(getMessages(locale).get(resourceId));
	}

	@Override
	public Optional<String> getLabel(Locale locale, String resourceId) {
		return Optional.ofNullable(getLabels(locale).get(resourceId));
	}

	@Override
	public ResourceDto getResource() {
		return getResource(Locale.getDefault());
	}

	@Override
	public Map<String, String> getMessages() {
		return getResource().getMessages();
	}

	@Override
	public Map<String, String> getLabels() {
		return getResource().getLabels();
	}

	@Override
	public Optional<String> getMessage(String resourceId) {
		return Optional.ofNullable(getMessages().get(resourceId));
	}

	@Override
	public Optional<String> getLabel(String resourceId) {
		return Optional.ofNullable(getLabels().get(resourceId));
	}
}
