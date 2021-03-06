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
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.service.ClientService;
import com.wha.springmvc.service.ConseillerService;

@RestController
public class ConseillerRestController {
	
	
	
	
	 @Autowired
	 ConseillerService conseillerService;  //Service which will do all data retrieval/manipulation work
	 
	 @Autowired
	 ClientService clientService;
	 
	 //-------------------Retrieve All Conseillers--------------------------------------------------------
     
	    @RequestMapping(value = "/administrator/conseillers/", method = RequestMethod.GET)
	    public ResponseEntity<List<Conseiller>> listAllConseillers() {
	        List<Conseiller> conseillers = conseillerService.findAllConseillers();
	        if(conseillers.isEmpty()){
	            return new ResponseEntity<List<Conseiller>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Conseiller>>(conseillers, HttpStatus.OK);
	    }
	 
	 
	    
	    //-------------------Retrieve Single Conseiller--------------------------------------------------------
	     
	    @RequestMapping(value = "/administrator/conseiller/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Conseiller> getConseiller(@PathVariable("id") int id) {
	        System.out.println("Fetching Conseiller with id " + id);
	        Conseiller Conseiller = conseillerService.findById(id);
	        if (Conseiller == null) {
	            System.out.println("Conseiller with id " + id + " not found");
	            return new ResponseEntity<Conseiller>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Conseiller>(Conseiller, HttpStatus.OK);
	    }
	 
	     
	     
	    //-------------------Create a Conseiller--------------------------------------------------------
	     
	    @RequestMapping(value = "/administrator/conseiller/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createConseiller(@RequestBody Conseiller conseiller,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Conseiller " + conseiller.getUsername() + conseiller.getEmail()+ conseiller.getAddress());
	 
	        if (conseillerService.isConseillerExist(conseiller)) {
	            System.out.println("A Conseiller with name " + conseiller.getUsername() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	 
	        conseillerService.saveConseiller(conseiller);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/conseiller/{id}").buildAndExpand(conseiller.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	    
	     
	    //------------------- Update a Conseiller --------------------------------------------------------
	     
	    @RequestMapping(value = "/administrator/conseiller/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Conseiller> updateConseiller(@PathVariable("id") int id, @RequestBody Conseiller conseiller) {
	        System.out.println("Updating Conseiller " + id);
	         
	        Conseiller currentuser = conseillerService.findById(id);
	         
	        if (currentuser==null) {
	            System.out.println("Conseiller with id " + id + " not found");
	            return new ResponseEntity<Conseiller>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentuser.setUsername(conseiller.getUsername());
	        currentuser.setAddress(conseiller.getAddress());
	        currentuser.setEmail(conseiller.getEmail());
	         
	        conseillerService.updateConseiller(currentuser);
	        return new ResponseEntity<Conseiller>(currentuser, HttpStatus.OK);
	    }
	 
	  //------------------Associate a client to conseiller------------------------------------------------    

	    @RequestMapping(value = "/administrator/client/conseiller/{id}/", method = RequestMethod.POST)
	    public ResponseEntity<Void> updateClient(@PathVariable("id") int id, @RequestBody Client client, UriComponentsBuilder ucBuilder) {
	        System.out.println("Create client " + client + "associate to conseiller " + id);
	    
	        Conseiller currentCons = conseillerService.findById(id);
	         
	        
	        
	        if (clientService.isUserExist(client)) {
	            System.out.println("A Client with name " + client.getUsername() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	        
	        client.setConseiller(currentCons);
	     
	         
	        clientService.saveClient(client);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/conseiller/{id}").buildAndExpand(client.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	    
	
	    
	    
	    //------------------- Delete a Conseiller --------------------------------------------------------
	     
	    @RequestMapping(value = "/administrator/conseiller/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Conseiller> deleteConseiller(@PathVariable("id") int id) {
	        System.out.println("Fetching & Deleting Conseiller with id " + id);
	 
	        Conseiller conseiller = conseillerService.findById(id);
	        List<Client> listClient = conseiller.getClients();
	        
	        
	        if (listClient != null) {
	        	 System.out.println("Unable to delete. Conseiller with id " + id + " has clients");
		         return new ResponseEntity<Conseiller>(HttpStatus.METHOD_NOT_ALLOWED);
	        }
	        conseillerService.deleteConseillerById(id);
	        return new ResponseEntity<Conseiller>(HttpStatus.NO_CONTENT);
	        
	    }
	 
	     
	    
	    //------------------- Delete All Conseillers --------------------------------------------------------
	     
	    @RequestMapping(value = "/administrator/conseiller/", method = RequestMethod.DELETE)
	    public ResponseEntity<Conseiller> deleteAllConseillers() {
	        System.out.println("Deleting All Conseillers");
	 
	        conseillerService.deleteAllConseillers();
	        return new ResponseEntity<Conseiller>(HttpStatus.NO_CONTENT);
	    }
	 
	}

