package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.entity.Kullanicilar;
import com.services.KullaniciServis;

@ManagedBean(name = "kullaniciManagedBean")
@SessionScoped
public class KullaniciBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{kullaniciServis}")
	private KullaniciServis kullaniciServis;
	private List<Kullanicilar> liste;
	private int id;
	private String ad;
	private String soyad;
	private String tel;
	private int captchaEnter;
	private int captcha;
	private Kullanicilar selectedKullanici = new Kullanicilar();
	private Boolean disableuncellePopUp = false;

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

	public int getCaptcha() {
		return captcha;
	}

	public void setCaptcha(int captcha) {
		this.captcha = captcha;
	}

	public List<Kullanicilar> getListe() {
		return liste;
	}

	public void setListe(List<Kullanicilar> liste) {
		this.liste = liste;
	}

	public KullaniciServis getKullaniciServis() {
		return kullaniciServis;
	}

	public void setKullaniciServis(KullaniciServis kullaniciServis) {
		this.kullaniciServis = kullaniciServis;
	}

	public int getCaptchaEnter() {
		return captchaEnter;
	}

	public void setCaptchaEnter(int captchaEnter) {
		this.captchaEnter = captchaEnter;
	}

	public Kullanicilar getSelectedKullanici() {
		return selectedKullanici;
	}

	public void setSelectedKullanici(Kullanicilar selectedKullanici) {
		this.selectedKullanici = selectedKullanici;
	}

	@PostConstruct
	public void init() {
		System.out.println("INIT CALISTI");
		liste = new ArrayList<Kullanicilar>();
		liste = kullaniciServis.kullanicilariGetir();
		captchaSet();
	}
	
	public void refreshContext()
	{
		init();
	}

	public void tabloDoldur() {
		liste = new ArrayList<Kullanicilar>();
		liste = kullaniciServis.kullanicilariGetir();
	}

	public void addKullaniciBean() {
		if (getCaptchaEnter() == getCaptcha()) {
			Kullanicilar k = new Kullanicilar();
			k.setId(getId());
			k.setAd(getAd());
			k.setSoyad(getSoyad());
			k.setTel(getTel());
			kullaniciServis.addKullaniciServis(k);
		} else {
			FacesMessage errMsg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"YANLIS CAPTCHA", "");
			FacesContext.getCurrentInstance().addMessage("errorMsg",
					errMsg);
		}
		
		tabloDoldur();
		

	}

	public void deleteKullanici(Kullanicilar k) {
		kullaniciServis.deleteKullaniciServis(k);
		tabloDoldur();
	}

	public void captchaSet() {
		setCaptcha((int) (Math.random() * 100000 + 1));
	}

	public void popUpAc() {
		RequestContext.getCurrentInstance().openDialog("guncelle");
	}

	public void handleKullanici(Kullanicilar k) {
		selectedKullanici = new Kullanicilar();
		selectedKullanici = k;
	}

	public Boolean getDisableuncellePopUp() {
		return disableuncellePopUp;
	}

	public void setDisableuncellePopUp(Boolean disableuncellePopUp) {
		this.disableuncellePopUp = disableuncellePopUp;
	}

	

}
