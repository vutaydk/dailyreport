package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.Task;

public class TaskRepo implements IRepository<Task> {

	public static TaskRepo model;
	static {
		model = new TaskRepo();
	}

	public List<Task> getAll() {
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
