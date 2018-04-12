package model.repo.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.User;

@RequestScoped
public class UserRepoImpl implements IUserRepo {
	@Inject
	private DBConnector connector;

	@Override
	public List<User> getAll() {
		TypedQuery<User> query = connector.createQuery("FROM " + User.class.getName(), User.class);
		return query.getResultList();
	}

	public Optional<User> find(int id) {
		TypedQuery<User> query = connector.createQuery("FROM " + User.class.getName() + " WHERE id=:id", User.class);
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());

	}

	public Optional<User> check(String em, String pwd) {
		TypedQuery<User> query = connector
				.createQuery("FROM " + User.class.getName() + " WHERE employee_code=:em AND password=:pwd", User.class);
		query.setParameter("em", em);
		query.setParameter("pwd", pwd);
		List<User> result = query.getResultList();
		if (result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(result.get(0));
	}

	@Override
	public boolean insert(User user) {
		user.setCreatedAt(new Date());
		connector.insert(user);
		return true;
	}

	@Override
	public boolean update(User user) {
		connector.update(user);
		return true;
	}

	@Override
	public boolean delete(User user) {
		connector.delete(user);
		return true;
	}

}
