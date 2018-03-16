package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import model.entity.Project;

public class ProjectDao {

	private final String TABLE = "FROM " + Project.class.getName();
	private Query<Project> query;
	private String select;

	public ProjectDao select(String[] array) {
		select = "SELECT ";
		for (String string : array) {
			select = select + string + ", ";
		}
		System.out.println(select);

		return this;
	}

	public static void main(String[] args) {
		String[] str = { "hanh", "hanh", "hanh" };
		new ProjectDao().select(str);
	}

	// public ProjectDao where(HashMap<String, Object> map) {
	//
	// select = "SELECT " + opt.get();
	//
	// return this;
	// }

	@SuppressWarnings("unchecked")
	public List<Project> get() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Project> list = null;
		try {
			session.beginTransaction();
			query = session.createQuery(TABLE);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public Project find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Project project = null;
		try {
			session.beginTransaction();
			query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id");
			query.setParameter("id", id);
			project = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return project;
	}

	public boolean insert(Project object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			object.setCreatedAt(new Date());
			session.save(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

	public boolean update(Project object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

	public boolean delete(Project object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

}
