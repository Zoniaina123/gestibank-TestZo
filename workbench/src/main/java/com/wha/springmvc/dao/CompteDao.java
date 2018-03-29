package com.wha.springmvc.dao;



import com.wha.springmvc.model.Compte;

public interface CompteDao {
	
	//creer un compte associer à un client
	void save(Compte compte);
	
	Compte findByNumeroCompte(int numCompte);
		
	
}
