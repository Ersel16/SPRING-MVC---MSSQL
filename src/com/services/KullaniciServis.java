package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.KullaniciDao;
import com.entity.Kullanicilar;

@Service("kullaniciServis")
@Transactional
public class KullaniciServis {

	@Autowired
	private KullaniciDao kullaniciDao;

	public KullaniciDao getKullaniciDao() {
		return kullaniciDao;
	}

	public void setKullaniciDao(KullaniciDao kullaniciDao) {
		this.kullaniciDao = kullaniciDao;
	}

	public void addKullaniciServis(Kullanicilar k) {
		kullaniciDao.addKullanici(k);
	}

	public void deleteKullaniciServis(Kullanicilar k) {
		kullaniciDao.deleteKullanici(k);
	}

	public void updateKullaniciServis(Kullanicilar k) {
		kullaniciDao.updateKullanici(k);
	}

	public List<Kullanicilar> kullanicilariGetir() {
		List<Kullanicilar> k = new ArrayList<Kullanicilar>();
		k = kullaniciDao.kullaniciListeleDao();
		return k;
	}

}
