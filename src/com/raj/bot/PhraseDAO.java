package com.raj.bot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.raj.eliza.model.Phrase;

public class PhraseDAO {

    public static void create(Phrase entry) {
        EntityManager em = EMFService.get().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            em.persist(entry);
            em.detach(entry);
			transaction.commit();
        } finally {
            em.close();
        }
    }

    public static void delete(Phrase entry) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            em.remove(entry);
        } finally {
            em.close();
        }
    }

    public static void update(Phrase entry) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            em.merge(entry);
        } finally {
            em.close();
        }
    }

    public static Phrase find(Phrase entry) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            return em.find(Phrase.class, entry);
        } finally {
            em.close();
        }
    }

    public static Phrase findById(Phrase entry) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Query q = em.createQuery("select t from Phrase t where t.id=?");
            // q.setParameter(1, entry.getId());
            return (Phrase) q.getResultList().get(0);
        } finally {
            em.close();
        }
    }

    public static List<Phrase> getLatestEntries(int start, int count) {
        EntityManager em = EMFService.get().createEntityManager();
        List<Phrase> todos = null;
        try {
            Query q = em.createQuery("select t from Phrase t");
            // q.setFirstResult(start);
            // q.setMaxResults(count);
            todos = new ArrayList(q.getResultList());
        } finally {
            em.close();
        }
        return todos;
    }
}
