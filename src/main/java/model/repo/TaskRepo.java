package model.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Task;

@Log4j
public class TaskRepo implements IRepository<Task> {

	public List<Task> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Task> tasks;
		try {
			session.beginTransaction();
			Query<Task> query = session.createQuery("FROM " + Task.class.getName(), Task.class);
			tasks = query.getResultList();
		} catch (Exception e) {
			tasks = new ArrayList<>();
			log.debug(e);
		}

		return tasks;
	}

	public Optional<Task> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<Task> optional;
		try {
			session.beginTransaction();
			Query<Task> query = session.createQuery("FROM " + Task.class.getName() + " WHERE id=:id", Task.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			optional = Optional.empty();
			log.debug(e);
		}

		return optional;
	}

	public boolean insert(Task task) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			task.setCreatedAt(new Date());
			session.save(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Task task) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Task task) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
