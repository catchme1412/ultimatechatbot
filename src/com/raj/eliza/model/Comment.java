package com.raj.eliza.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class Comment {
	
	 @Id
	 // @Column(name = "ID")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Key id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	// @JoinColumn(name = "PHRASE_GROUP_ID", referencedColumnName = "ID")
	// @JoinTable(name = "PHRASE_GROUP", joinColumns = @JoinColumn(name = "ID"))
	private PhraseGroup phraseGroup;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	// @JoinColumn(name = "ANSWER_GROUP_ID", referencedColumnName = "ID")
	// @JoinTable(name = "ANSWER_GROUP", joinColumns = @JoinColumn(name = "ID"))
	private AnswerGroup answerGroup;

	public PhraseGroup getPhraseGroup() {
		return phraseGroup;
	}

	public void setPhraseGroup(PhraseGroup phraseGroup) {
		this.phraseGroup = phraseGroup;
	}

	public AnswerGroup getAnswerGroup() {
		return answerGroup;
	}

	public void setAnswerGroup(AnswerGroup answerGroup) {
		this.answerGroup = answerGroup;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", Phrase=" + getPhraseGroup().getPhrases().iterator().next().getPhraseText()
				+ ", Answer=" + getAnswerGroup().getAnswers().iterator().next().getAnswerText() + "]";
	}

}