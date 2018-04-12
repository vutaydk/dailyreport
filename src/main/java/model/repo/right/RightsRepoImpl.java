package model.repo.right;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.Rights;
import model.repo.IRepository;

@RequestScoped
public class RightsRepoImpl implements IRepository<Rights> {

	@Inject
	private DBConnector connector;

	@Override
	public List<Rights> getAll() {
		TypedQuery<Rights> query = connector.createQuery("FROM " + Rights.class.getName(), Rights.class);
		return query.getResultList();
	}

	public Optional<Rights> find(int id) {
		TypedQuery<Rights> query = connector.createQuery("FROM " + Rights.class.getName() + " WHERE id=:id",
				Rights.class);
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());

	}

	@Override
	public boolean insert(Rights rights) {
		rights.setCreatedAt(new Date());
		connector.insert(rights);
		return true;
	}

	@Override
	public boolean update(Rights rights) {
		connector.update(rights);
		return true;
	}

	@Override
	public boolean delete(Rights rights) {
		connector.delete(rights);
		return true;
	}

}
