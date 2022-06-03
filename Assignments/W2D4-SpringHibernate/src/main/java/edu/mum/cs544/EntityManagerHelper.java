package edu.mum.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
    private static final EntityManagerFactory emf; 
    private static final ThreadLocal<EntityManager> threadLocal;

    static {
        emf = Persistence.createEntityManagerFactory("cs544");      
        threadLocal = new ThreadLocal<EntityManager>();
    }

    public static EntityManager getCurrent() {
        EntityManager em = threadLocal.get();
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }
}