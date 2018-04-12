package common.util;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import lombok.Getter;
import model.entity.EntityBase;

@ApplicationScoped
@Startup
@Singleton
public class DBConnector {

	@Getter
	@PersistenceContext(name = "dailyreport")
	private EntityManager entityManager;

	public static Session getSession() {
		return null;
	}

	public <T> TypedQuery<T> createQuery(String queryString, Class<T> resultWrapper) {
		return this.entityManager.createQuery(queryString, resultWrapper);
	}
	
	public <T> TypedQuery<T> createNamedQuery(String queryString, Class<T> resultWrapper) {
		return this.entityManager.createNamedQuery(queryString, resultWrapper);
	}

	public void update(EntityBase entity) {
		this.entityManager.persist(entity);
	}

	public void insert(EntityBase entity) {
		this.entityManager.persist(entity);
	}

	public void delete(EntityBase entity) {
		this.entityManager.remove(entity);
	}

}
