package com.wha.springmvc.dao;



import javax.persistence.NoResultException;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;

public class CompteDaoImpl extends AbstractDao<Integer, Compte> implements CompteDao {


	@Override
	public void save(Compte compte) {
		persist(compte);
		
	}

	@Override
	public Compte findByNumeroCompte(int numCompte) {
		System.out.println("Account number: "+ numCompte);
		try {
			Compte compte = (Compte) getEntityManager().createQuery("SELECT c FROM Compte c WHERE c.numeroCompte LIKE :numeroCompte").setParameter("numeroCompte", numCompte).getSingleResult();
			return compte;
			
		} catch (NoResultException ex) {
			return null;
		}
	}

}
