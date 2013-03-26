package com.raj.eliza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.appengine.api.datastore.Key;

@Entity
public class AuxiliaryVerb {

	@Id
//	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;

	private String original;

	private String tranformed;

	public String getOriginal() {
		return original;
	}

	public String getTransformed() {
		return getTranformed();
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getTranformed() {
		return tranformed;
	}

	public void setTranformed(String tranformed) {
		this.tranformed = tranformed;
	}

	public Key getId() {
		return id;
	}

}
