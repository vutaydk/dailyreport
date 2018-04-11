package model.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.extern.log4j.Log4j;
import model.entity.Report;

@Log4j
public class ReportRepo implements IRepository<Report> {

	public static ReportRepo model;
	static {
		model = new ReportRepo();
	}

	@Override
	public List<Report> getList() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Report> list = entityManager.createQuery("SELECT e FROM Report e", Report.class).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return list;
	}

	public Optional<Report> find(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<Report> project = Optional.ofNullable(entityManager.find(Report.class, id));
		entityManager.close();
		entityManagerFactory.close();
		return project;
	}

	@Override
	public boolean persist(Report project) {
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
	public boolean remove(Report project) {
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
