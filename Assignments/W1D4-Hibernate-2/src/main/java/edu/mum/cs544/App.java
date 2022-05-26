package edu.mum.cs544;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

public class App {

    private static EntityManagerFactory emf;


    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        TypedQuery<Students> query = em.createQuery("from edu.mum.cs544.Students",Students.class);
        List<Students> studentsList = query.getResultList();
        print(studentsList);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(Students.builder().id(455).email("ram@email.com").name("RAM").build());
        em.getTransaction().commit();
        em.close();

        em=emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Students> query1 = em.createQuery("from edu.mum.cs544.Students",Students.class);
        List<Students> students = query1.getResultList();
        print(students);
        em.getTransaction().commit();
        em.close();

    }

    private static void print(List<Students> studentsList) {

        studentsList.forEach(s -> System.out.println("NAME: "+s.getName()+", EMAIL: "+s.getEmail()));
    }
}
