package com.raj.eliza.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class AuxiliaryVerbGroup {

//	@Id
//	@Column(name = "ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	protected Long id;
	@Id
//	@Column (name ="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
//	
	
	private Double priority;
	
	protected String description;
	
	@OneToMany(mappedBy = "auxiliaryVerbGroup", cascade = CascadeType.ALL)
	private Collection <AuxiliaryVerb> auxiliaryVerbs;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPriority() {
		return priority;
	}

	public void setPriority(Double priority) {
		this.priority = priority;
	}

	/**
//	 * @return the id
//	 */
//	public Key getId() {
//		return id;
//	}
//


	
	
	public AuxiliaryVerbGroup() {
		auxiliaryVerbs = new HashSet<AuxiliaryVerb>() ;
	}

	public void add(AuxiliaryVerb auxVerb) {
		auxiliaryVerbs.add(auxVerb);
	}
	
}
