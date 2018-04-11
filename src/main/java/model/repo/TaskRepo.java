package model.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.extern.log4j.Log4j;
import model.entity.Task;

@Log4j
public class TaskRepo implements IRepository<Task> {

	public static TaskRepo model;
	static {
		model = new TaskRepo();
	}

	@Override
	public List<Task> getList() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Task> list = entityManager.createQuery("SELECT e FROM Task e", Task.class).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return list;
	}

	public Optional<Task> find(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<Task> project = Optional.ofNullable(entityManager.find(Task.class, id));
		entityManager.close();
		entityManagerFactory.close();
		return project;
	}

	@Override
	public boolean persist(Task project) {
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
	public boolean remove(Task project) {
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
