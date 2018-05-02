package model.repo.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import common.exception.BusinessException;
import common.exception.message.ErrorMessage;
import common.i18n.ResourceType;
import common.util.DBConnector;
import model.entity.i18n.SystemResource;

@RequestScoped
public class SystemResourceRepo implements ISystemResourceRepo {

	@Inject
	private DBConnector connection;

	@Override
	public List<SystemResource> getAll() {
//		TypedQuery<SystemResource> query = connection.getEntityManager()
//				.createQuery("FROM " + SystemResource.class.getName(), SystemResource.class);
//		List<SystemResource> resources = query.getResultList();

		return new ArrayList<>();
	}

	@Override
	public boolean insert(SystemResource t) {
		throw new BusinessException(new ErrorMessage("it is read only"));
	}

	@Override
	public boolean update(SystemResource t) {
		throw new BusinessException(new ErrorMessage("it is read only"));
	}

	@Override
	public boolean delete(SystemResource t) {
		throw new BusinessException(new ErrorMessage("it is read only"));
	}

	@Override
	public List<SystemResource> getAllMessages(Locale locale) {
		TypedQuery<SystemResource> query = connection.getEntityManager().createQuery("FROM "
				+ SystemResource.class.getName() + " e WHERE e.pk.resourceType = :type AND e.pk.locale =:locale",
				SystemResource.class);
		query.setParameter("type", ResourceType.MESSAGE);
		query.setParameter("locale", locale.toString());
		List<SystemResource> resources = query.getResultList();

		return resources;
	}

	@Override
	public List<SystemResource> getAllLabel(Locale locale) {
		TypedQuery<SystemResource> query = connection.getEntityManager().createQuery("FROM "
				+ SystemResource.class.getName() + " e WHERE e.pk.resourceType = :type AND e.pk.locale =:locale",
				SystemResource.class);
		query.setParameter("type", ResourceType.LABEL);
		query.setParameter("locale", locale.toString());
		List<SystemResource> resources = query.getResultList();

		return resources;
	}
}
