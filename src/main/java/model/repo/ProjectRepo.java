package model.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import common.util.HibernateUtil;
import model.entity.Project;

public class ProjectRepo implements IRepository<Project> {

	public List<Project> getAll() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Project> projects;
		Query<Project> query = session.createQuery("FROM " + Project.class.getName(), Project.class);
		projects = query.getResultList();
		transaction.commit();

		return projects;
	}

	public Optional<Project> find(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Project> optional;
		try {
			Query<Project> query = session.createQuery("FROM " + Project.class.getName() + " WHERE id=:id",
					Project.class);
			query.setParameter("id", id);
			optional = Optional.ofNullable(query.getSingleResult());
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			optional = Optional.empty();
			e.printStackTrace();
		}

		return optional;
	}

	public boolean insert(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			project.setCreatedAt(new Date());
			session.save(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean update(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

	public boolean delete(Project project) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(project);
			session.getTransaction().commit();
		} catch (Exception e) {
			transaction.commit();
			e.printStackTrace();
		}

		return session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
	}

}
