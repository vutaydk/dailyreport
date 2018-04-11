package model.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import lombok.extern.log4j.Log4j;
import model.entity.User;

@Log4j
public class UserRepo implements IRepository<User> {

	public static UserRepo model;
	static {
		model = new UserRepo();
	}

	@Override
	public List<User> getList() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<User> list = entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return list;
	}

	public Optional<User> find(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<User> project = Optional.ofNullable(entityManager.find(User.class, id));
		entityManager.close();
		entityManagerFactory.close();
		return project;
	}

	public Optional<User> check(String em, String pwd) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT e FROM User e WHERE e.employeeCode=:em AND e.password=:pwd",
				User.class);
		query.setParameter("em", em);
		query.setParameter("pwd", pwd);
		List<User> list = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		if (!list.isEmpty())
			return Optional.ofNullable(list.get(0));
		return Optional.empty();
	}

	@Override
	public boolean persist(User project) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(project);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			log.debug(e);
			return false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

	@Override
	public boolean remove(User project) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(project);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			log.debug(e);
			return false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

}
