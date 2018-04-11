package model.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.extern.log4j.Log4j;
import model.entity.Project;

@Log4j
public class ProjectRepo implements IRepository<Project> {

	public static ProjectRepo model;
	static {
		model = new ProjectRepo();
	}

	@Override
	public List<Project> getList() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Project> list = entityManager.createQuery("SELECT e FROM Project e", Project.class).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return list;
	}

	public Optional<Project> find(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<Project> project = Optional.ofNullable(entityManager.find(Project.class, id));
		entityManager.close();
		entityManagerFactory.close();
		return project;
	}

	@Override
	public boolean persist(Project project) {
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
	public boolean remove(Project project) {
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
