package com.raj.eliza.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class DataLoaderUtil {
	
	private Collection<Comment> comments = new ArrayList<Comment>();
	private Collection<AuxiliaryVerb> auxVerbs = new ArrayList<AuxiliaryVerb>();
	
	public DataLoaderUtil(InputStreamReader stream) throws IOException {
		BufferedReader reader = new BufferedReader(stream);
		int phraseGroupPriority = 0;
		int answerPriority = 0;
		int phrasePriority = 0;
		boolean isPhrase = true;
		boolean isAnswer = false;
		boolean isAuxVerb = false;
		PhraseGroup phraseGroup = null;
		AnswerGroup answerGroup = null;
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			if (line.trim().length() == 0) {
				continue;
			}
			if (line.equals("PHRASE")) {
				line = reader.readLine();
				if (phraseGroup != null && answerGroup != null) {
					Comment comment = new Comment();
					comment.setAnswerGroup(answerGroup);
					comment.setPhraseGroup(phraseGroup);
					getComments().add(comment);
					phraseGroup = null;
				}
				isPhrase = true;
				isAuxVerb = false;
				isAnswer = false;
				phraseGroup = new PhraseGroup();
				phraseGroup.setPriority((double) phraseGroupPriority++);
//				phrasePriority = 0;
			} else if (line.equals("ANSWER")) {
				line = reader.readLine();
				isPhrase = false;
				isAuxVerb = false;
				isAnswer = true;
				answerGroup = new AnswerGroup();
				answerGroup.setAnswers(new ArrayList<Answer>());
			} else if (line.equals("AUXVERB")) {
				line = reader.readLine();
				isPhrase = false;
				isAuxVerb = true;
				isAnswer = false;
			}
			if (isPhrase) {
				Phrase phrase = new Phrase();
				phrase.setPhraseGroup(phraseGroup);
				phrase.setPriority(phrasePriority++);
				phrase.setPhraseText(line);
				phraseGroup.add(phrase);
			}
			if (isAnswer) {
				Answer answer = new Answer();
//				answer.setAnswerGroup(answerGroup);
				answer.setAnswerText(line);
//				answer.setPhraseGroup(phraseGroup);
				answer.setPriority(answerPriority++);
				answerGroup.addAnswer(answer);
			}
			
			if (isAuxVerb) {
				AuxiliaryVerb auxVerb = new AuxiliaryVerb();
				line = reader.readLine();
				auxVerb.setOriginal(line);
				line = reader.readLine();
				auxVerb.setTranformed(line);
				getAuxVerbs().add(auxVerb);
			}
			
		}
		reader.close();
		for (Comment c: getComments()) {
			System.out.println(c);
		}
	}
	
	public static void main(String[] args) throws IOException {
		FileInputStream stream = new FileInputStream("/home/rkv/workspace/ultimatechatbot/src/META-INF/eliza.dat");
//		new DataLoaderUtil(stream);
	}

	/**
	 * @return the comments
	 */
	public Collection<Comment> getComments() {
		return comments;
	}

	/**
	 * @return the auxVerbs
	 */
	public Collection<AuxiliaryVerb> getAuxVerbs() {
		return auxVerbs;
	}



}
