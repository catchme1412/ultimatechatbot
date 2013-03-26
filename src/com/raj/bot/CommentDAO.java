package com.raj.bot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.raj.eliza.model.Comment;

public class CommentDAO {

	public static void create(Comment entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.persist(entry);
		} finally {
			em.close();
		}
	}

	public static void delete(Comment entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.remove(entry);
		} finally {
			em.close();
		}
	}
	
	public static void update(Comment entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.merge(entry);
		} finally {
			em.close();
		}
	}

	public static Comment find(Comment entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			return em.find(Comment.class, entry);
		} finally {
			em.close();
		}
	}
	
	public static List<Comment> getLatestEntries(int start, int count) {
		EntityManager em = EMFService.get().createEntityManager();
		List<Comment> todos = null;
		try {
			Query q = em.createQuery("select q from Comment q");
			todos = new ArrayList(q.getResultList());
		} finally {
			em.close();
		}
		return todos;
	}
}
