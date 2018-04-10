package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Report;

@Log4j
public class ReportRepo implements IRepository<Report> {

	public static ReportRepo model;
	static {
		model = new ReportRepo();
	}

	@Override
	public List<Report> getAll() {
		log.debug("get all");
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
		log.debug("find id=" + id);
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

	@Override
	public boolean insert(Report report) {
		log.debug("insert: " + report);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			report.setCreatedAt(new Date());
			session.save(report);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean update(Report report) {
		log.debug("update: " + report);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(report);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean delete(Report report) {
		log.debug("delete: " + report);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(report);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
