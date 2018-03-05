package com.dailyreport.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.dailyreport.conn.HibernateUtil;
import com.dailyreport.model.Rights;

public class RightsDAO {

	private Session session;

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Rights> getAllRights() {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List<Rights> rights = new ArrayList<>();
			Criteria criteria = session.createCriteria(Rights.class);
			rights = criteria.list();
			session.getTransaction().commit();
			return rights;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("main method");
		RightsDAO rightsDAO = new RightsDAO();
		for (Rights r : rightsDAO.getAllRights()) {
			System.out.println(r.getName());
		}
	}
}
