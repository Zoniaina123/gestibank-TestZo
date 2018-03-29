package com.wha.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wha.springmvc.model.Administrator;
import com.wha.springmvc.service.AdministratorService;

@RestController
public class AdministratorRestController {
	
	
	@Autowired
    AdministratorService administratorService;  //Service which will do all data retrieval/manipulation work
	
	
	
	 //-------------------Retrieve All Admins--------------------------------------------------------
    
    @RequestMapping(value = "/administrators/", method = RequestMethod.GET)
    public ResponseEntity<List<Administrator>> listAllUsers() {
        List<Administrator> admins = administratorService.findAllAdministrators();
        if(admins.isEmpty()){
            return new ResponseEntity<List<Administrator>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Administrator>>(admins, HttpStatus.OK);
    }
    
    
    
  //-------------------Create an Administrator--------------------------------------------------------
    
    @RequestMapping(value = "/administrator/", method = RequestMethod.POST)
    public ResponseEntity<Void> createAdministrator(@RequestBody Administrator admin,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Adminnistrator " + admin.getUsername() + admin.getEmail()+ admin.getAddress());
 
        if (administratorService.isUsExist(admin)) {
            System.out.println("A Administrator with name " + admin.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
administratorService.saveAdministrator(admin);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/{id}").buildAndExpand(admin.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    
    

    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "//administrator/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Administrator> deleteAdministrator(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Admin with id " + id);
 
        Administrator admin = administratorService.findById(id);
        if (admin == null) {
            System.out.println("Unable to delete. admin with id " + id + " not found");
            return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
        }
 
        administratorService.deleteAdministratorById(id);
        return new ResponseEntity<Administrator>(HttpStatus.NO_CONTENT);
    }

}
