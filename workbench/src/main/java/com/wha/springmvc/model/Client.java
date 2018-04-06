package com.wha.springmvc.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 */

@Entity(name = "Client")
@DiscriminatorValue("CLIENT")
public class Client extends User {

	private String nom;
	private int nbEnfant;
	private String situation;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CONSEILLER_ID")
	private Conseiller conseiller;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="client")
    private List<Compte> comptes;

	public Client() {
		
	}

	public Client(String nom, int nbEnfant, String situation, Conseiller conseiller, List<Compte> comptes) {
		super();
		this.nom = nom;
		this.nbEnfant = nbEnfant;
		this.situation = situation;
		this.conseiller = conseiller;
		this.comptes = comptes;
	}

	
	
	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public int getNbEnfant() {
		return nbEnfant;
	}



	public void setNbEnfant(int nbEnfant) {
		this.nbEnfant = nbEnfant;
	}



	public String getSituation() {
		return situation;
	}



	public void setSituation(String situation) {
		this.situation = situation;
	}



	public Conseiller getConseiller() {
		return conseiller;
	}



	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}



	public List<Compte> getComptes() {
		return comptes;
	}



	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}



	public void voirCompte() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void imprimerHistorique() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void effectuerVirement() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void voirNotif() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void DemandeChequier() {
		// TODO implement here
	}

}