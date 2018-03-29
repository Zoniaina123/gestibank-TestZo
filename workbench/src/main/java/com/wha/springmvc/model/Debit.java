package com.wha.springmvc.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Debit")
@DiscriminatorValue("Debit")
public class Debit extends Operation {
	
	public Debit()
	{
		
	}
	public Debit(int numopr, double montant, Date date, String libelle, Compte compte) {
		super(numopr, montant, date, libelle, compte);
		// TODO Auto-generated constructor stub
	}

	//redéfini la méthode getMoins
	public double getMoins() {
			return super.getMontant();
		}

	

    
   
}