package com.wha.springmvc.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrator;

@Repository("AdministratorDao")
public class AdministratorDaoImpl extends AbstractDao<Integer, Administrator> implements AdminitratorDao {

	

	// An alternative to Hibernate.initialize()
	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

	@Override
	public void save(Administrator admin) {
		persist(admin);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrator> findAlldAmins() {
		List<Administrator> admins = getEntityManager()
				.createQuery("SELECT a FROM Administrator a ORDER BY a.username ASC").getResultList();

		return admins;
	}

	@Override
	public Administrator findByName(String name) {
		System.out.println("name: "+ name);
		try {
			Administrator admin = (Administrator) getEntityManager().createQuery("SELECT a FROM Administrator a WHERE a.username LIKE :name").setParameter("name", name).getSingleResult();
			return admin;
			
		} catch (NoResultException ex) {
			return null;
		}
	}


	@Override
	public Administrator findById(int id) {
		
		Administrator admin = getByKey(id);
		return admin;
		
		
	}

	@Override
	public void deleteAdministratorById(int id) {
		Administrator admin = getByKey(id);
		delete(admin);
		
	}



}
