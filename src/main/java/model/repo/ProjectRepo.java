package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import common.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import model.entity.Project;

@Log4j
public class ProjectRepo implements IRepository<Project> {

	public static ProjectRepo model;
	static {
		model = new ProjectRepo();
	}

	public List<Project> getAll() {
		log.debug("get all");
		Session session = HibernateUtil.getSession();
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName(), Project.class);
			return query.getResultList();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Optional<Project> find(int id) {
		log.debug("find id=" + id);
		Session session = HibernateUtil.getSession();
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id",
					Project.class);
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

	public boolean insert(Project project) {
		log.debug("insert: " + project);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			project.setCreatedAt(new Date());
			session.save(project);
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

	public boolean update(Project project) {
		log.debug("update: " + project);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(project);
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

	public boolean delete(Project project) {
		log.debug("delete: " + project);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(project);
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
