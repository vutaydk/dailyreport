package model.repo;

import java.util.ArrayList;
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

	public List<Report> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Report> reports = new ArrayList<>();
		Transaction transaction = session.beginTransaction();
		Query<Report> query = session.createQuery("FROM " + Report.class.getName(), Report.class);
		reports = query.getResultList();
		transaction.commit();

		return reports;
	}

	public Optional<Report> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Report> optional = Optional.empty();
		try {
			Query<Report> query = session.createQuery("FROM " + Report.class.getName() + " WHERE id=:id", Report.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return optional;
	}

	public boolean insert(Report report) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			report.setCreatedAt(new Date());
			session.save(report);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Report report) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(report);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Report object) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
