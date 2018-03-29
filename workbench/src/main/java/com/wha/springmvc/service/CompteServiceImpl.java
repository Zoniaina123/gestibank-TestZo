package com.wha.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.wha.springmvc.dao.ClientDao;
import com.wha.springmvc.dao.CompteDao;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;

public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteDao dao;
	
	@Autowired 
	private ClientDao daoCli;

	@Override
	public void save(Compte compte) {
		dao.save(compte);
		
	}
	
	//associer un compte à un client
	@Override
	public void updateCompte(Compte compte, int idClient) {
	Compte entity = dao.findByNumeroCompte(compte.getNumeroCompte());
	
	Client cli = daoCli.findById(idClient);
		if(entity!=null){
			entity.setClient(cli);	
			}
		dao.save(entity);
	}

	@Override
	public Compte findByNumeroCompte(int numCompte) {
		return dao.findByNumeroCompte((int) numCompte);
	}
	
	@Override
	public boolean isAccountExist(Compte compte) {
		return dao.findByNumeroCompte((int) compte.getNumeroCompte())!=null;
	}

	

}
