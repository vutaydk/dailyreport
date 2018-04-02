package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Task;

@Log4j
public class TaskRepo implements IRepository<Task> {

	public static TaskRepo model;
	static {
		model = new TaskRepo();
	}

	public List<Task> getAll() {
		log.debug("get all");
		Session session = HibernateUtil.getSession();
		try {
			Query<Task> query = session.createQuery("FROM " + Task.class.getName(), Task.class);
			return query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Optional<Task> find(int id) {
		log.debug("find id=" + id);
		Session session = HibernateUtil.getSession();
		try {
			Query<Task> query = session.createQuery("FROM " + Task.class.getName() + " WHERE id=:id", Task.class);
			query.setParameter("id", id);
			if (query.getResultList().size() > 0)
				return Optional.ofNullable(query.getSingleResult());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return Optional.empty();
	}

	public boolean insert(Task task) {
		log.debug("insert: " + task);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			task.setCreatedAt(new Date());
			session.save(task);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Task task) {
		log.debug("update: " + task);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(task);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Task task) {
		log.debug("delete: " + task);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(task);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
