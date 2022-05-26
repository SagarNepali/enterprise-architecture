package edu.mum.cs544;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Consumer;

public class App {

    private static EntityManagerFactory emf;

    static void printAllStudents(){
        apply(entityManager -> {
            TypedQuery<Students> query = entityManager.createQuery("from edu.mum.cs544.Students",Students.class);
            List<Students> studentsList = query.getResultList();
            print(studentsList);
        });
    }

    static void insertOne(){
        apply(entityManager -> {
            entityManager.persist(Students.builder().id(444).email("ram@email.com").name("RAM").build());
        });
    }

    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("cs544");

        printAllStudents();
        insertOne();
        printAllStudents();


    }

    private static void print(List<Students> studentsList) {

        studentsList.forEach(s -> System.out.println("NAME: "+s.getName()+", EMAIL: "+s.getEmail()));
    }

    private static void apply(Consumer<EntityManager> consumer){
        try {
            final EntityManager em  = emf.createEntityManager();
            em.getTransaction().begin();

            consumer.accept(em);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
