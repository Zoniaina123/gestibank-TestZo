package com.wha.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wha.springmvc.dao.ClientDao;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;



@Service("ClientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	
	
	@Autowired
	private ClientDao dao;
	@Override
	public Client findById(long id) {
		return dao.findById((int)id);
	}

	@Override
	public Client findByName(String name) {
		return dao.findByName((String) name);
	}

	@Override
	public void saveClient(Client client) {
		dao.save(client);

	}

	@Override
	public void updateClient(Client client) {
Client entity = dao.findById((int)client.getId());
		if(entity!=null){
			entity.setUsername(client.getUsername());
			entity.setAddress(client.getAddress());
			entity.setEmail(client.getEmail());
			
		}
		dao.save(entity);
	}

	@Override
	public void deleteClientById(long id) {
		dao.deleteClientById((int) id);

	}

	@Override
	public List<Client> findAllClients() {
		return dao.findAllClients();
	
	}

	@Override
	public void deleteAllClients() {
		dao.deleteAllClients();

	}

	@Override
	public boolean isUserExist(Client client) {
		return dao.findByName((String) client.getUsername())!=null;
	}

	//liste des comptes d'un client
	@Override
	public List<Compte> findbyIdClient(int idClient) {
		return dao.findbyIdClient(idClient);
	}
	
}
