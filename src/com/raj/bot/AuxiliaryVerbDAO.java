package com.raj.bot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.raj.eliza.model.AuxiliaryVerb;

public class AuxiliaryVerbDAO {

	public static void create(AuxiliaryVerb entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			
			em.persist(entry);
			em.detach(entry);
		} finally {
			em.close();
		}
	}

	public static void delete(AuxiliaryVerb entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.remove(entry);
		} finally {
			em.close();
		}
	}
	
	public static void update(AuxiliaryVerb entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.merge(entry);
		} finally {
			em.close();
		}
	}

	public static AuxiliaryVerb find(AuxiliaryVerb entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			return em.find(AuxiliaryVerb.class, entry);
		} finally {
			em.close();
		}
	}
	
	public static AuxiliaryVerb findById(AuxiliaryVerb entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Query q = em.createQuery("select t from AuxiliaryVerb t where t.id=?");
//			q.setParameter(1, entry.getId());
			return (AuxiliaryVerb) q.getResultList().get(0);
		} finally {
			em.close();
		}
	}

	public static List<AuxiliaryVerb> getLatestEntries(int start, int count) {
		EntityManager em = EMFService.get().createEntityManager();
		List<AuxiliaryVerb> todos = null;
		try {
			Query q = em.createQuery("select t from AuxiliaryVerb t");
//			q.setFirstResult(start);
//			q.setMaxResults(count);
			todos = new ArrayList(q.getResultList());
		} finally {
			em.close();
		}
		return todos;
	}
}
