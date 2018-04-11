package model.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.extern.log4j.Log4j;
import model.entity.ReportDetail;

@Log4j
public class ReportDetailRepo implements IRepository<ReportDetail> {

	public static ReportDetailRepo model;
	static {
		model = new ReportDetailRepo();
	}

	@Override
	public List<ReportDetail> getList() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<ReportDetail> list = entityManager.createQuery("SELECT e FROM ReportDetail e", ReportDetail.class)
				.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return list;
	}

	public Optional<ReportDetail> find(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<ReportDetail> project = Optional.ofNullable(entityManager.find(ReportDetail.class, id));
		entityManager.close();
		entityManagerFactory.close();
		return project;
	}

	@Override
	public boolean persist(ReportDetail project) {
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
	public boolean remove(ReportDetail project) {
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
