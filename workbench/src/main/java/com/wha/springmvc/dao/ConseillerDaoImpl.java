package com.wha.springmvc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
@Repository("conseillerDao")
public class ConseillerDaoImpl extends AbstractDao<Integer, Conseiller> implements ConseillerDao {



	
	
	
	
	
	
	
	
	
	///avec les exemples
	@Override
	public Conseiller findById(int id) {
		Conseiller conseiller = getByKey(id);
		return conseiller;
	}

	@Override
	public Conseiller findByName(String name) {
		System.out.println("name: "+ name);
		try {
			Conseiller conseiller = (Conseiller) getEntityManager().createQuery("SELECT c FROM Conseiller c WHERE c.username LIKE :name").setParameter("name", name).getSingleResult();
			return conseiller;
			
		} catch (NoResultException ex) {
			return null;
		}
	
	}

	@Override
	public void save(Conseiller conseiller) {
		persist(conseiller);

	}

	@Override
	public void deleteConseillerById(int id) {
		Conseiller conseiller = getByKey(id);
		delete(conseiller);

	}

	@Override
	public List<Conseiller> findAllConseillers() {
		@SuppressWarnings("unchecked")
		List<Conseiller> conseiller = getEntityManager().createQuery("SELECT c FROM Conseiller c ORDER BY c.username ASC").getResultList();				
		return conseiller;
	}

	@Override
	public void deleteAllConseillers() {
		// TODO Auto-generated method stub

	}

	
}
