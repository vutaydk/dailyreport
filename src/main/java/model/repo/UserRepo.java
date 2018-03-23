package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.User;

public class UserRepo implements IRepository<User> {

	public static UserRepo model;
	static {
		model = new UserRepo();
	}

	public List<User> getAll() {
		Session session = HibernateUtil.getSession();
		try {
			Query<User> query = session.createQuery("FROM " + User.class.getName(), User.class);
			return query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Optional<User> find(int id) {
		Session session = HibernateUtil.getSession();
		try {
			Query<User> query = session.createQuery("FROM " + User.class.getName() + " WHERE id=:id", User.class);
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

	public Optional<User> check(String em, String pwd) {
		Session session = HibernateUtil.getSession();
		try {
			Query<User> query = session.createQuery(
					"FROM " + User.class.getName() + " WHERE employee_code=:em AND password=:pwd", User.class);
			query.setParameter("em", em);
			query.setParameter("pwd", pwd);
			if (query.getResultList().size() > 0)
				return Optional.ofNullable(query.getSingleResult());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return Optional.empty();
	}

	public boolean insert(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			user.setCreatedAt(new Date());
			session.save(user);
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

	public boolean update(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(user);
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

	public boolean delete(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(user);
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
