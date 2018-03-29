package com.wha.springmvc.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;





@Entity(name = "CompteRemunerateur")
@DiscriminatorValue("CompteRemunerateur")
public class CompteRemunerateur extends Compte {
	
	
	private static final long serialVersionUID = 1L;
	private double taux ;

	public CompteRemunerateur() {
		
	}
	
	public CompteRemunerateur(int numeroCompte, double solde, Date dateCreation, Client client,
			List<Operation> operations) {
		super(numeroCompte, solde, dateCreation, client, operations);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */



	public double getTaux() {
		return taux;
	}



	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	
	
	

}
