package com.dailyreport.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dailyreport.model.Project;
import com.dailyreport.util.HibernateUtil;

public class ProjectDao {

	private final String SELECT = "FROM " + Project.class.getName();
	
	@SuppressWarnings("unchecked")
	public List<Project> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Project> list = null;
		try {
			session.beginTransaction();
			Query<Project> query = session.createQuery(SELECT);
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
	public Project find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Project project = null;
		try {
			session.beginTransaction();
			Query<Project> query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id");
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
