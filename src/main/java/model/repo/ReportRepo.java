package model.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Report;

@Log4j
public class ReportRepo implements IRepository<Report> {

	public List<Report> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Report> reports = new ArrayList<>();
		try {
			session.beginTransaction();
			Query<Report> query = session.createQuery("FROM " + Report.class.getName(), Report.class);
			reports = query.getResultList();
		} catch (Exception e) {
			log.debug(e);
		}

		return reports;
	}

	public Optional<Report> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<Report> optional = Optional.empty();
		try {
			session.beginTransaction();
			Query<Report> query = session.createQuery("FROM " + Report.class.getName() + " WHERE id=:id", Report.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			log.debug(e);
		}

		return optional;
	}

	public boolean insert(Report report) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			report.setCreatedAt(new Date());
			session.save(report);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Report report) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(report);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Report object) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
