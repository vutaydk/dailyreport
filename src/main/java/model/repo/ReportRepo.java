package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.Report;

public class ReportRepo implements IRepository<Report> {

	public static ReportRepo model;
	static {
		model = new ReportRepo();
	}

	public List<Report> getAll() {
		Session session = HibernateUtil.getSession();
		try {
			Query<Report> query = session.createQuery("FROM " + Report.class.getName(), Report.class);
			return query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Optional<Report> find(int id) {
		Session session = HibernateUtil.getSession();
		try {
			Query<Report> query = session.createQuery("FROM " + Report.class.getName() + " WHERE id=:id", Report.class);
			query.setParameter("id", id);
			if (query.getResultList().size() > 0)
				return Optional.ofNullable(query.getSingleResult());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return Optional.empty();
	}

	public boolean insert(Report report) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			report.setCreatedAt(new Date());
			session.save(report);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Report report) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(report);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Report object) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(object);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
