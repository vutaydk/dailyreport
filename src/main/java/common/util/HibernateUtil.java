package common.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			System.out.println("SessionFatory: " + (sessionFactory != null));
		}
		return sessionFactory;
	}

}
