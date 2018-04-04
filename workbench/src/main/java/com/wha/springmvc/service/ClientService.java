package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;

public interface ClientService {
	
	
Client findById(long id);
	
Client findByName(String name);
	
	void saveClient(Client client);
	
	void updateClient(Client client);
	
	//associer un client à un conseiller
	void updateConseillerClient(String userNameClient,int idConseiller);
	
	void deleteClientById(long id);

	List<Client> findAllClients(); 
	
	void deleteAllClients();
	
	public boolean isUserExist(Client client);
	
	//liste des comptes d'un client
	List<Compte> findbyIdClient(int idClient);
	
	//
	List<Client> findByConseillerId(int id);

}
