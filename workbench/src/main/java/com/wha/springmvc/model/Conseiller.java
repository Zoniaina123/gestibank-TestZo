package com.wha.springmvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity(name = "Conseiller")
@DiscriminatorValue("CONSEILLER")

public class Conseiller extends User {


	private int matricule;

	private Date dateDebut;
	
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy="conseiller")
    private List<Client> clients;
	
	@ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="ADMIN_ID")
	private Administrator administrator;
	
	//@OneToMany(mappedBy = "conseiller", cascade = CascadeType.ALL, orphanRemoval = true)
	//private Client clients;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
	private Administrator administrator;*/
	
	
	
	
	

	/**
	 * Default constructor
	 */
	public Conseiller() {
	}

	public Conseiller(int matricule, Date dateDebut, List<Client> clients, Administrator administrator) {
		super();
		this.matricule = matricule;
		this.dateDebut = dateDebut;
		this.clients = clients;
		this.administrator = administrator;
	}



	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	

	
	


	@Override
	public String toString() {
		return "Conseiller [matricule=" + matricule + ", dateDebut=" + dateDebut + ", clients=" + /*clients +*/ "]";
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	

}