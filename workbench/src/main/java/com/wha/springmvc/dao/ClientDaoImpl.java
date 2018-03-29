package com.wha.springmvc.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
@Repository("ClientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {
	
	
	
	// An alternative to Hibernate.initialize()
		protected void initializeCollection(Collection<?> collection) {
			if (collection == null) {
				return;
			}
			collection.iterator().hasNext();
		}

	@Override
	public Client findById(int id) {
		Client client = getByKey(id);
		return client;
	}

	@Override
	public Client findByName(String name) {
		System.out.println("name: "+ name);
		try {
			Client client = (Client) getEntityManager().createQuery("SELECT c FROM Client c WHERE c.username LIKE :name").setParameter("name", name).getSingleResult();
			return client;
			
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void save(Client client) {
		persist(client);

	}

	@Override
	public void deleteClientById(int id) {
		Client client = getByKey(id);
		delete(client);

	}
	@SuppressWarnings("unchecked")
	public List<Client> findAllClients() {
		
		List<Client> clients = getEntityManager().createQuery("SELECT c FROM Client c ORDER BY c.username ASC").getResultList();				
		return clients;
		
	}

	@Override
	public void deleteAllClients() {
		// TODO Auto-generated method stub

	}
	
	//affiche la liste des comptes d'un client
		@Override
		public List<Compte> findbyIdClient(int idClient) {
			Client client = getByKey(idClient);
			List<Compte> listCompte=client.getComptes();
			return listCompte;
		}

}
