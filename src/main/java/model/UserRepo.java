package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.User;

public class UserRepo implements IRepository{

	@SuppressWarnings("unchecked")
	public List<User> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> list = null;
		try {
			session.beginTransaction();
			Query<User> query = session.createQuery("FROM " + User.class.getName());
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
	public User find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;
		try {
			session.beginTransaction();
			Query<User> query = session.createQuery("FROM " + User.class.getName() + " WHERE id=:id");
			query.setParameter("id", id);
			user = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public User check(String em, String pwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;
		try {
			session.beginTransaction();
			Query<User> query = session
					.createQuery("FROM " + User.class.getName() + " WHERE employee_code=:em AND password=:pwd");
			query.setParameter("em", em);
			query.setParameter("pwd", pwd);
			user = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
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

	@Override
	public Object find() {
		// TODO Auto-generated method stub
		return null;
	}

}
