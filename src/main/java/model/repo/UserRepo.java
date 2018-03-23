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
import model.entity.User;

public class UserRepo implements IRepository<User> {

	public List<User> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<User> users;
		Transaction transaction = session.beginTransaction();
		Query<User> query = session.createQuery("FROM " + User.class.getName(), User.class);
		users = query.getResultList();
		transaction.commit();

		return users;
	}

	public Optional<User> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<User> optional;
		try {
			session.beginTransaction();
			Query<User> query = session.createQuery("FROM " + User.class.getName() + " WHERE id=:id", User.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			optional = Optional.empty();
			e.printStackTrace();
		}

		return optional;
	}

	public Optional<User> check(String em, String pwd) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<User> optional;
		try {
			session.beginTransaction();
			Query<User> query = session.createQuery(
					"FROM " + User.class.getName() + " WHERE employee_code=:em AND password=:pwd", User.class);
			query.setParameter("em", em);
			query.setParameter("pwd", pwd);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			optional = Optional.empty();
			e.printStackTrace();
		}

		return optional;
	}

	public boolean insert(User user) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			user.setCreatedAt(new Date());
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(User user) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(User user) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
