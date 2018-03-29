package com.wha.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wha.springmvc.dao.AdminitratorDao;
import com.wha.springmvc.model.Administrator;
@Service("AdministratorService")
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdminitratorDao dao;
	
	@Override
	public void saveAdministrator(Administrator admin) {
		dao.save(admin);

	}

	

	
	

	@Override
	public List<Administrator> findAllAdministrators() {
		return dao.findAlldAmins();
	}



	public Administrator findByName(String name) {

		return dao.findByName((String) name);
	}
	



	@Override
	public boolean isUsExist(Administrator admin) {
		return dao.findByName((String) admin.getUsername())!=null;
	}



	@Override
	public void deleteAdministratorById(int id) {
		dao.deleteAdministratorById(id);
		
	}






	@Override
	public Administrator findById(int id) {
		return dao.findById((int)id);
	}

	

}
