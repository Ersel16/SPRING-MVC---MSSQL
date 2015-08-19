package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.behavior.ajax.AjaxBehaviorHandler;
import org.primefaces.component.ajaxstatus.AjaxStatus;
import org.primefaces.context.RequestContext;

import com.entity.Kullanicilar;
import com.services.KullaniciServis;
import com.util.HibernateUtil;

@ManagedBean(name = "kullaniciGuncelleBean")
@RequestScoped
public class KullaniciGuncelleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{kullaniciServis}")
	private KullaniciServis kullaniciServis;
	@ManagedProperty(value = "#{kullaniciManagedBean}")
	private KullaniciBean kullaniciBean;
	private Kullanicilar kullanicilar = new Kullanicilar();
	private int id;
	private String ad;
	private String soyad;
	private String tel;

	public KullaniciServis getKullaniciServis() {
		return kullaniciServis;
	}

	public void setKullaniciServis(KullaniciServis kullaniciServis) {
		this.kullaniciServis = kullaniciServis;
	}

	public KullaniciBean getKullaniciBean() {
		return kullaniciBean;
	}

	public void setKullaniciBean(KullaniciBean kullaniciBean) {
		this.kullaniciBean = kullaniciBean;
	}

	public Kullanicilar getKullanicilar() {
		return kullanicilar;
	}

	public void setKullanicilar(Kullanicilar kullanicilar) {
		this.kullanicilar = kullanicilar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@PostConstruct
	public void init() {
		setId(getKullaniciBean().getSelectedKullanici().getId());
		setAd(getKullaniciBean().getSelectedKullanici().getAd());
		setSoyad(getKullaniciBean().getSelectedKullanici().getSoyad());
		setTel(getKullaniciBean().getSelectedKullanici().getTel());
	}

	public void update() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			session.beginTransaction();
			Query q;
			q = session.createQuery("update FROM Kullanicilar SET ID=" + getId()
					+ ", AD='" + getAd() + "', SOYAD='" + getSoyad()
					+ "', TEL='"
					+ getKullaniciBean().getSelectedKullanici().getTel()
					+ "' WHERE ID="+getKullaniciBean().getSelectedKullanici().getId()+"");
			q.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
		}
		
		closeDialog();
		refresh();
	}
	
	
	public void closeDialog()
	{
		RequestContext.getCurrentInstance().closeDialog("guncelle");
	}
	
	public void refresh()
	{
		getKullaniciBean().init();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:tablo");
		RequestContext.getCurrentInstance().update("form:tablo");
		
	}
}
