package com.raj.eliza.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;
@Entity
public class Answer {
	
	@Id
//	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn (name="ANSWER_GROUP_ID", referencedColumnName="ID")
	private AnswerGroup answerGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn (name="PHRASE_GROUP_ID", referencedColumnName="ID")
	private PhraseGroup phraseGroup;
	
	private String answerText;
	
	private float frustrationFactor;
	
	private long priority;
	
	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}


	public AnswerGroup getAnswerGroup() {
		return answerGroup;
	}

	public void setAnswerGroup(AnswerGroup answerGroup) {
		this.answerGroup = answerGroup;
	}

	public PhraseGroup getPhraseGroup() {
		return phraseGroup;
	}

	public void setPhraseGroup(PhraseGroup phraseGroup) {
		this.phraseGroup = phraseGroup;
	}
	
	/**
	 * @return the frustrationFactor
	 */
	public float getFrustrationFactor() {
		return frustrationFactor;
	}

	/**
	 * @param frustrationFactor the frustrationFactor to set
	 */
	public void setFrustrationFactor(float frustrationFactor) {
		this.frustrationFactor = frustrationFactor;
	}


//	public Key getId() {
//		return id;
//	}
	
	@Override
	public String toString() {
		return "Answer [phraseGroup=" + phraseGroup + ", answerText=" + answerText + ", priority=" + priority + "]";
	}

}
