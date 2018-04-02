package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Conseiller;



public interface ConseillerDao {
	
	//avec les services que le conseiller peut faire


	
	
	///en suivant l'exemple
	Conseiller findById(int id);
	
	Conseiller findByName(String name);
	
	void save(Conseiller conseiller);
	
	void deleteConseillerById(int id);
	
	List<Conseiller> findAllConseillers();
	
	void deleteAllConseillers();

}
