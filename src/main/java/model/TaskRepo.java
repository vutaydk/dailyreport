package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Task;

public class TaskRepo implements IRepository<Task> {

	public List<Task> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Task> list = null;
		try {
			Query<Task> query = session.createQuery("FROM " + Task.class.getName(), Task.class);
			list = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public Task find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Task object = null;
		try {
			Query<Task> query = session.createQuery("FROM " + Task.class.getName() + " WHERE id=:id", Task.class);
			query.setParameter("id", id);
			object = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
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
