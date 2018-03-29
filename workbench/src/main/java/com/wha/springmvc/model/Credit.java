package com.wha.springmvc.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Credit")
@DiscriminatorValue("Credit")
public class Credit extends Operation {
	
	public Credit() {
		
	}
	
	public Credit(int numopr, double montant, Date date, String libelle, Compte compte) {
		super(numopr, montant, date, libelle, compte);
		// TODO Auto-generated constructor stub
	}


	//redéfini la méthode getPlus
	public double getPlus() {
		return super.getMontant();
	}
    


}