package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Report;

public class ReportRepo implements IRepository<Report>{

	@SuppressWarnings("unchecked")
	public List<Report> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Report> list = null;
		try {
			session.beginTransaction();
			Query<Report> query = session.createQuery("FROM " + Report.class.getName());
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
	public Report find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Report report = null;
		try {
			session.beginTransaction();
			Query<Report> query = session.createQuery("FROM " + Report.class.getName() + " WHERE id=:id");
			query.setParameter("id", id);
			report = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return report;
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

	@Override
	public Report find() {
		// TODO Auto-generated method stub
		return null;
	}

}
