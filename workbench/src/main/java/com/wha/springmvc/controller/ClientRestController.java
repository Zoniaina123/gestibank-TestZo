package com.wha.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.User;
import com.wha.springmvc.service.ClientService;

@RestController
public class ClientRestController {
	
	
	@Autowired
   ClientService clientService;  //Service which will do all data retrieval/manipulation work
	
	
	
	 //-------------------Retrieve All Clients --------------------------------------------------------
    
    @RequestMapping(value = "/clients/", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.findAllClients();
        if(clients.isEmpty()){
            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }
    
    

	 //-------------------Retrieve All Clients --------------------------------------------------------
   
   @RequestMapping(value = "/conseiller/{id}/clients/", method = RequestMethod.GET)
   public ResponseEntity<List<Client>> listAllClientsConseiller(@PathVariable("id") int id) {
       List<Client> clients = clientService.findByConseillerId(id);
       if(clients.isEmpty()){
           return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
       }
       return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
   }
    
    
//-------------------Retrieve All Comptes--------------------------------------------------------
    
    @RequestMapping(value = "/conseiller/client/{idClient}/comptes/", method = RequestMethod.GET)
    public ResponseEntity<List<Compte>> listAllConseillers(@PathVariable("id") int idClient) {
        List<Compte> comptes = clientService.findbyIdClient(idClient);
        if(comptes.isEmpty()){
            return new ResponseEntity<List<Compte>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Compte>>(comptes, HttpStatus.OK);
    }
    
    
  //-------------------Retrieve Single User--------------------------------------------------------
    
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") int id) {
        System.out.println("Fetching Client with id " + id);
        Client client = clientService.findById(id);
        if (client == null) {
            System.out.println("Client with id " + id + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
    
    
//-------------------Create a Client--------------------------------------------------------
    
    @RequestMapping(value = "/client/", method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(@RequestBody Client client,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Client " + client.getUsername() + client.getEmail()+ client.getAddress());
 
        if (clientService.isUserExist(client)) {
            System.out.println("A Client with name " + client.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        clientService.saveClient(client);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/{id}").buildAndExpand(client.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    
    
  //------------------- Update a Client --------------------------------------------------------
    
    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Client> updateClient(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println("Updating Client " + id);
         
        Client currentUser = clientService.findById(id);
         
        if (currentUser==null) {
            System.out.println("Client with id " + id + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
         
        clientService.updateClient(currentUser);
        return new ResponseEntity<Client>(currentUser, HttpStatus.OK);
    }
    
    

    //------------------- Delete a Client --------------------------------------------------------
     
    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteClient(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Client with id " + id);
 
        Client client = clientService.findById(id);
        if (client == null) {
            System.out.println("Unable to delete. Client with id " + id + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
 
        clientService.deleteClientById(id);
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }
    
    
    //------------------- Delete All Clients --------------------------------------------------------
    
    @RequestMapping(value = "/client/", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteAllClients() {
        System.out.println("Deleting All Clients");
 
        clientService.deleteAllClients();
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }

}


