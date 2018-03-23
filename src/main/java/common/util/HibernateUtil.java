package common.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		Configuration conf = new Configuration().configure();
		sessionFactory = conf.buildSessionFactory();
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
