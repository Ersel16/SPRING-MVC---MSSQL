package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entity.Kullanicilar;
import com.util.HibernateUtil;

@Repository
public class KullaniciDaoImpl implements KullaniciDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addKullanici(Kullanicilar k) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(k);
			session.getTransaction().commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
	}

	public void deleteKullanici(Kullanicilar k) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(k);
			session.getTransaction().commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
	}

	public void updateKullanici(Kullanicilar k) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(k);
			session.getTransaction().commit();
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Kullanicilar> kullaniciListeleDao() {
		List<Kullanicilar> liste = new ArrayList<Kullanicilar>();
		Query q;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			q = session.createQuery("from Kullanicilar");
			liste = q.list();
			session.getTransaction().commit();
			session.close();
			return liste;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return null;

		}
	}

}
