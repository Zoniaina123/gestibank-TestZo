package com.wha.springmvc.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity(name = "CompteSansDecouvert")
@DiscriminatorValue("CompteSansDecouvert")
public class CompteSansDecouvert extends Compte {
	
	
	private static final long serialVersionUID = 1L;
	private double decouvert ;

	public CompteSansDecouvert() {
		
	}
	
	public CompteSansDecouvert(int numeroCompte, double solde, Date dateCreation, Client client,
			List<Operation> operations) {
		super(numeroCompte, solde, dateCreation, client, operations);
		decouvert=0;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	/**
	 * 
	 */
	
	

	
	
	
	
	

}
