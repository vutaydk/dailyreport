package model.repo.i18n;

import java.util.List;
import java.util.Locale;

import model.entity.i18n.SystemResource;
import model.repo.IRepository;

public interface ISystemResourceRepo extends IRepository<SystemResource> {
	List<SystemResource> getAllMessages(Locale locale);

	List<SystemResource> getAllLabel(Locale locale);
}
