package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.User;

@Log4j
public class UserRepo implements IRepository<User> {

	public static UserRepo model;
	static {
		model = new UserRepo();
	}

	public List<User> getAll() {
		log.debug("get all");
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
		log.debug("find id=" + id);
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
		log.debug("check user=" + em + ", password=" + pwd);
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
		log.debug("insert: " + user);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			user.setCreatedAt(new Date());
			session.save(user);
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

	public boolean update(User user) {
		log.debug("update: " + user);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(user);
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

	public boolean delete(User user) {
		log.debug("delete: " + user);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(user);
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
