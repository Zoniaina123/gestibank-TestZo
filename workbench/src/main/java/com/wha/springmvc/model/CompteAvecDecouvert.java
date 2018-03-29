package com.wha.springmvc.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;




@Entity(name = "CompteAvecDecouvert")
@DiscriminatorValue("CompteAvecDecouvert")
public class CompteAvecDecouvert extends Compte {

	
	private static final long serialVersionUID = 1L;
	private double decouvert;


	public CompteAvecDecouvert() {
		
	}
	
	public CompteAvecDecouvert(int numeroCompte, double solde, Date dateCreation, Client client,
			List<Operation> operations,double montantDecouv) {
		super(numeroCompte, solde, dateCreation, client, operations);
		// TODO Auto-generated constructor stub
		decouvert=montantDecouv;
	}
	/**
	 * 
	 */


	public double getDecouvert() {
		return decouvert;
	}


	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	

	

	

}
