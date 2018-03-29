package com.wha.springmvc.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "Administrator")
@DiscriminatorValue("ADMIN")

public class Administrator extends User {
	
	@OneToMany(mappedBy="administrator")
    private List<Conseiller> conseillers;

	public Administrator() {
		
	}

	public Administrator(List<Conseiller> conseillers) {
		super();
		this.conseillers = conseillers;
	}

	

	public List<Conseiller> getConseillers() {
		return conseillers;
	}

	public void setConseillers(List<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}
	
	
	
	
	

}
