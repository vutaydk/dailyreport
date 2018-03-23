package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Project;

@Log4j
public class ProjectRepo implements IRepository<Project> {

	public Optional<List<Project>> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		Optional<List<Project>> optional = Optional.empty();
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName(), Project.class);
			optional = Optional.ofNullable(query.getResultList());
		} catch (Exception e) {
			log.debug(e);
		}

		return optional;
	}

	public Optional<Project> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<Project> optional = Optional.empty();
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id",
					Project.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			log.debug(e);
		}

		return optional;
	}

	public boolean insert(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			project.setCreatedAt(new Date());
			session.save(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
