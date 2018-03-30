package com.wha.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.service.ClientService;
import com.wha.springmvc.service.CompteService;

public class CompteRestController {

	@Autowired
	CompteService compteService;
	
	@Autowired
	ClientService clientService;
	
//-------------------Create an account--------------------------------------------------------
    
    @RequestMapping(value = "/compte/", method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(@RequestBody Compte compte,    UriComponentsBuilder ucBuilder) {
        System.out.println("Compte " + compte.getNumeroCompte() + "a été créé");
 
        if (compteService.isAccountExist(compte)) {
            System.out.println("An account with id " + compte.getNumeroCompte() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        compteService.save(compte);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/compte/{numeroCompte}").buildAndExpand(compte.getNumeroCompte()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
  //------------------Associate an account to a client------------------------------------------------    

    @RequestMapping(value = "/compte/{numeroCompte}/client/{idClient}/", method = RequestMethod.PUT)
    public ResponseEntity<Compte> updateCompte(@PathVariable("numeroCompte") int numeroCompte, @RequestBody Compte compte, @PathVariable("idClient") int idClient, @RequestBody Client client) {
        System.out.println("Updating account " + numeroCompte + "for client" + idClient);
         
        Compte currentCompte = compteService.findByNumeroCompte(numeroCompte);
         
        if (currentCompte==null) {
            System.out.println("Compte with id " + numeroCompte + " not found");
            return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
        }
 
        Client cli = clientService.findById((int) idClient);
        
        currentCompte.setClient(cli);
     
         
        compteService.updateCompte(currentCompte,idClient);
        return new ResponseEntity<Compte>(currentCompte, HttpStatus.OK);
    }
    
    
}
