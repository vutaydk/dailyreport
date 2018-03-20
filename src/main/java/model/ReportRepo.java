package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Report;

public class ReportRepo implements IRepository<Report> {

	public List<Report> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Report> list = null;
		try {
			Query<Report> query = session.createQuery("FROM " + Report.class.getName(), Report.class);
			list = query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public Report find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Report object = null;
		try {
			Query<Report> query = session.createQuery("FROM " + Report.class.getName() + " WHERE id=:id", Report.class);
			query.setParameter("id", id);
			object = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return object;
	}

	public boolean insert(Report object) {
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

	public boolean update(Report object) {
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

	public boolean delete(Report object) {
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
