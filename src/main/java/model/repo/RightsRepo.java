package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Rights;

@Log4j
public class RightsRepo implements IRepository<Rights> {

	public Optional<List<Rights>> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		Optional<List<Rights>> optional = Optional.empty();
		try {
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName(), Rights.class);
			optional = Optional.ofNullable(query.getResultList());
		} catch (Exception e) {
			log.debug(e);
		}

		return optional;
	}

	public Optional<Rights> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<Rights> optional = Optional.empty();
		try {
			Query<Rights> query = session.createQuery("FROM " + Rights.class.getName() + " WHERE id=:id", Rights.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			log.debug(e);
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
			log.debug(e);
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
			log.debug(e);
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
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
