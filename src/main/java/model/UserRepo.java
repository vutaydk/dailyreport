package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.User;

public class UserRepo implements IRepository<User> {

	public List<User> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> list = null;
		try {
			Query<User> query = session.createQuery("FROM " + User.class.getName(), User.class);
			list = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public User find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User object = null;
		try {
			Query<User> query = session.createQuery("FROM " + User.class.getName() + " WHERE id=:id", User.class);
			query.setParameter("id", id);
			object = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
	}

	public User check(String em, String pwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User object = null;
		try {
			Query<User> query = session.createQuery(
					"FROM " + User.class.getName() + " WHERE employee_code=:em AND password=:pwd", User.class);
			query.setParameter("em", em);
			query.setParameter("pwd", pwd);
			object = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
	}

	public boolean insert(User object) {
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

	public boolean update(User object) {
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

	public boolean delete(User object) {
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
