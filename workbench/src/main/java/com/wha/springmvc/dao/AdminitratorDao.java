package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Administrator;

public interface AdminitratorDao {
	
	Administrator findById(int id);

	Administrator findByName(String name);

	void save(Administrator admin);
	
	void deleteAdministratorById(int id);


	List<Administrator> findAlldAmins();

	

	

	

}
