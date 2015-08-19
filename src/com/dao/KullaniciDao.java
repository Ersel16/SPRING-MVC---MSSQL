package com.dao;

import java.util.List;

import com.entity.Kullanicilar;



public interface KullaniciDao {
	
	public void addKullanici(Kullanicilar k);
	

	public void deleteKullanici(Kullanicilar k);


	public void updateKullanici(Kullanicilar k);
	
	public List<Kullanicilar> kullaniciListeleDao();



}
