package com.wha.springmvc.model;


import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "operation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numopr;
	private double montant;
	private Date date;
	private String libelle;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPTE_ID")
	private Compte compte;
	
	public Operation() {
		
	}
	
	public Operation(int numopr, double montant, Date date, String libelle, Compte compte) {
		super();
		this.numopr = numopr;
		this.montant = montant;
		this.date = date;
		this.libelle = libelle;
		this.compte = compte;
	}


	//méthode pour avoir le montant credité
	public double getPlus() {
		return 0;
	}
	
	//méthode pour avoir le montant débité
		public double getMoins() {
			return 0;
		}

	public int getNumopr() {
		return numopr;
	}



	public void setNumopr(int numopr) {
		this.numopr = numopr;
	}


 
	public double getMontant() {
		return montant;
	}



	public void setMontant(double montant) {
		this.montant = montant;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public Compte getCompte() {
		return compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	
	
	
	
	
	
	

	

}