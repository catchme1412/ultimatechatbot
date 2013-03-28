package com.raj.eliza.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;



@Entity
public class PhraseGroup {

	@Id
//	@Column (name ="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	
	private Double priority;
	
	protected String description;
	
	@Basic
	@OneToMany(mappedBy = "phraseGroup", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//	@JoinColumn(name = "PHRASE_GROUP_ID", referencedColumnName="ID")
	private Collection<Phrase> phrases;
	
	private float rank;

	public PhraseGroup() {
		setPhraseSet(new HashSet());
	}

	public void add(Phrase phrase) {
		getPhrases().add(phrase);
	}

	public Collection<Phrase> getPhrases() {
		return phrases;
	}

	public void setPhraseSet(Set<Phrase> phraseSet) {
		this.phrases = phraseSet;
	}

	/**
	 * @return the rank
	 */
	public float getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(float rank) {
		this.rank = rank;
	}
	
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
	 * @return the id
	 */
	public Key getId() {
		return id;
	}


}
