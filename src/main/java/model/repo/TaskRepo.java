package model.repo;

import java.util.ArrayList;
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
		Optional<Task> optional;
		try {
			session.beginTransaction();
			Query<Task> query = session.createQuery("FROM " + Task.class.getName() + " WHERE id=:id", Task.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			optional = Optional.empty();
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
