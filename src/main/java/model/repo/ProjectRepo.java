package model.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.Project;

public class ProjectRepo implements IRepository<Project> {

	public List<Project> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Project> projects;
		try {
			session.beginTransaction();
			Query<Project> query = session.createQuery("FROM " + Project.class.getName(), Project.class);
			projects = query.getResultList();
		} catch (Exception e) {
			projects = new ArrayList<>();
			e.printStackTrace();
		}

		return projects;
	}

	public Optional<Project> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Optional<Project> optional;
		try {
			session.beginTransaction();
			Query<Project> query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id",
					Project.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
		} catch (Exception e) {
			optional = Optional.empty();
			e.printStackTrace();
		}

		return optional;
	}

	public boolean insert(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			project.setCreatedAt(new Date());
			session.save(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
