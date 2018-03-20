package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Project;

public class ProjectRepo implements IRepository<Project> {

	public List<Project> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Project> list = null;
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName(), Project.class);
			list = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public Project find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Project object = null;
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id",
					Project.class);
			query.setParameter("id", id);
			object = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
	}

	public boolean insert(Project object) {
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

	public boolean update(Project object) {
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

	public boolean delete(Project object) {
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
