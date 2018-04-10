package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.ReportDetail;

@Log4j
public class ReportDetailRepo implements IRepository<ReportDetail> {

	public static ReportDetailRepo model;
	static {
		model = new ReportDetailRepo();
	}

	@Override
	public List<ReportDetail> getAll() {
		log.debug("get all");
		Session session = HibernateUtil.getSession();
		try {
			Query<ReportDetail> query = session.createQuery("FROM " + ReportDetail.class.getName(), ReportDetail.class);
			return query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Optional<ReportDetail> find(int id) {
		log.debug("find id=" + id);
		Session session = HibernateUtil.getSession();
		try {
			Query<ReportDetail> query = session.createQuery("FROM " + ReportDetail.class.getName() + " WHERE id=:id",
					ReportDetail.class);
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
	public boolean insert(ReportDetail reportDetail) {
		log.debug("insert: " + reportDetail);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			reportDetail.setCreatedAt(new Date());
			session.save(reportDetail);
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
	public boolean update(ReportDetail report) {
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
	public boolean delete(ReportDetail report) {
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
			return true;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
