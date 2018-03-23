package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.User;

@Log4j
public class UserRepo implements IRepository<User> {

	public Optional<List<User>> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		Optional<List<User>> optional = Optional.empty();
		try {
			Query<User> query = session.createQuery("FROM " + User.class.getName(), User.class);
			optional = Optional.ofNullable(query.getResultList());
		} catch (Exception e) {
			log.debug(e);
		}

		return optional;
	}

	public Optional<User> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<User> optional = Optional.empty();
		try {
			Query<User> query = session.createQuery("FROM " + User.class.getName() + " WHERE id=:id", User.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			log.debug(e);
		}

		return optional;
	}

	public Optional<User> check(String em, String pwd) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<User> optional = Optional.empty();
		try {
			Query<User> query = session.createQuery(
					"FROM " + User.class.getName() + " WHERE employee_code=:em AND password=:pwd", User.class);
			query.setParameter("em", em);
			query.setParameter("pwd", pwd);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			log.debug(e);
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
			log.debug(e);
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
			log.debug(e);
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
			log.debug(e);
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
