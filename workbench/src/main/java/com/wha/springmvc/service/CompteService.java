package com.wha.springmvc.service;


import com.wha.springmvc.model.Compte;

public interface CompteService {

	//creer un compte associer à un client
	void save(Compte compte);
	
	Compte findByNumeroCompte(int numCompte);
	
	void updateCompte(Compte compte, int idClient);
	
	public boolean isAccountExist(Compte compte);
}
