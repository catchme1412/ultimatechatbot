package com.raj.eliza.model;

import java.util.Collection;

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
public class AnswerGroup  {

	@Id
//	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
//	
	private Double priority;
	
	protected String description;
	
	
//	@OneToMany (fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Basic
	@OneToMany(mappedBy = "answerGroup", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JoinColumn(name = "answerGroupId", referencedColumnName="ID")
	private Collection<Answer> answers;

	public Collection<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	@Override
	public String toString() {
		return "AnswerGroup [answers=" + answers + "]";
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

//	/**
//	 * @return the id
//	 */
//	public Key getId() {
//		return id;
//	}

}