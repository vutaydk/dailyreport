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

	public List<Task> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Task> tasks;
		Transaction transaction = session.beginTransaction();
		Query<Task> query = session.createQuery("FROM " + Task.class.getName(), Task.class);
		tasks = query.getResultList();
		transaction.commit();

		return tasks;
	}

	public Optional<Task> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Task> optional;
		try {
			Query<Task> query = session.createQuery("FROM " + Task.class.getName() + " WHERE id=:id", Task.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			optional = Optional.empty();
			e.printStackTrace();
		}

		return optional;
	}

	public boolean insert(Task task) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			task.setCreatedAt(new Date());
			session.save(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Task task) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Task task) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
