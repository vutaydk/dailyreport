package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Rights;

public class RightsRepo implements IRepository<Rights> {

	public List<Rights> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rights> list = null;
		try {
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName(), Rights.class);
			list = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public Rights find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Rights object = null;
		try {
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName() + " WHERE id=:id", Rights.class);
			query.setParameter("id", id);
			object = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
	}

	public boolean insert(Rights object) {
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

	public boolean update(Rights object) {
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

	public boolean delete(Rights object) {
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
