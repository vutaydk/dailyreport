package model.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.Rights;

public class RightsRepo implements IRepository<Rights> {

	public List<Rights> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Rights> rights;
		try {
			session.beginTransaction();
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName(), Rights.class);
			rights = query.getResultList();
		} catch (Exception e) {
			rights = new ArrayList<>();
			e.printStackTrace();
		}

		return rights;
	}

	public Optional<Rights> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<Rights> optional;
		try {
			session.beginTransaction();
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName() + " WHERE id=:id", Rights.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			optional = Optional.empty();
			e.printStackTrace();
		}

		return optional;
	}

	public boolean insert(Rights rights) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			rights.setCreatedAt(new Date());
			session.save(rights);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Rights rights) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(rights);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Rights rights) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(rights);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
