package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.Rights;

public class RightsRepo implements IRepository<Rights> {

	public static RightsRepo model;
	static {
		model = new RightsRepo();
	}

	public List<Rights> getAll() {
		Session session = HibernateUtil.getSession();
		try {
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName(), Rights.class);
			return query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Optional<Rights> find(int id) {
		Session session = HibernateUtil.getSession();
		try {
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName() + " WHERE id=:id", Rights.class);
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

	public boolean insert(Rights rights) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			rights.setCreatedAt(new Date());
			session.save(rights);
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

	public boolean update(Rights rights) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(rights);
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

	public boolean delete(Rights rights) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(rights);
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
