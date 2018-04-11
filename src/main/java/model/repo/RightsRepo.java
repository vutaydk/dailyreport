package model.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.extern.log4j.Log4j;
import model.entity.Rights;

@Log4j
public class RightsRepo implements IRepository<Rights> {

	public static RightsRepo model;
	static {
		model = new RightsRepo();
	}

	@Override
	public List<Rights> getList() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Rights> list = entityManager.createQuery("SELECT e FROM Rights e", Rights.class).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return list;
	}

	public Optional<Rights> find(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<Rights> project = Optional.ofNullable(entityManager.find(Rights.class, id));
		entityManager.close();
		entityManagerFactory.close();
		return project;
	}

	@Override
	public boolean persist(Rights project) {
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
	public boolean remove(Rights project) {
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
