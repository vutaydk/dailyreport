package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Task;

public class TaskDao {

	private final String SELECT = "FROM " + Task.class.getName();
	
	@SuppressWarnings("unchecked")
	public List<Task> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Task> list = null;
		try {
			session.beginTransaction();
			Query<Task> query = session.createQuery(SELECT);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public Task find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Task project = null;
		try {
			session.beginTransaction();
			Query<Task> query = session.createQuery("FROM " + Task.class.getName() + " WHERE id=:id");
			query.setParameter("id", id);
			project = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return project;
	}

	public boolean insert(Task object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			object.setCreatedAt(new Date());
			session.save(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

	public boolean update(Task object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

	public boolean delete(Task object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

}
