package com.raj.eliza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
@Entity
public class Phrase {

	public Phrase() {
//		System.out.println("MMMMMMMMMMMMM");
	}
	@Id
//	@Column (name ="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;

//	@JoinColumn(name = "PHRASE_GROUP_ID", referencedColumnName = "PHRASE_GROUP_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private PhraseGroup phraseGroup;

	@Column(name = "PHRASE_TEXT")
	private String phraseText;

	@Column(name = "PRIORITY")
	private long priority;

	public String getPhraseText() {
		return phraseText;
	}

	public void setPhraseText(String phraseText) {
		this.phraseText = phraseText;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public PhraseGroup getPhraseGroup() {
		return phraseGroup;
	}

	public void setPhraseGroup(PhraseGroup phraseGroup) {
		this.phraseGroup = phraseGroup;
	}

	public String toString() {
		return phraseText +":" + priority;
	}

	/**
	 * @return the id
	 */
	public Key getId() {
		return id;
	}


}
