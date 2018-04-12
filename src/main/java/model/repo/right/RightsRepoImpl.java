package model.repo.right;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.Rights;

@RequestScoped
public class RightsRepoImpl implements IRightRepo {
	@Inject
	private DBConnector connector;

	@Override
	public List<Rights> getAll() {
		TypedQuery<Rights> query = connector.createQuery("FROM " + Rights.class.getName(), Rights.class);
		return query.getResultList();
	}

	@Override
	public boolean insert(Rights rights) {
		rights.setCreatedAt(new Date());
		connector.insert(rights);
		return true;
	}

	@Override
	public boolean update(Rights rights) {
		rights.setUpdatedAt(new Date());
		connector.update(rights);
		return true;
	}

	@Override
	public boolean delete(Rights rights) {
		connector.delete(rights);
		return true;
	}

	@Override
	public Optional<Rights> findById(int id) {
		Rights rights = connector.getEntityManager().find(Rights.class, id);
		return Optional.ofNullable(rights);
	}
}
