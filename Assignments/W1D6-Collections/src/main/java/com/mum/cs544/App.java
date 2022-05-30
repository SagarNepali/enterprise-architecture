package com.mum.cs544;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class App {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("cs544");

        insertEmployees();


    }

    private static void insertEmployees(){
        apply(entityManager -> {
            Employee employee = Employee.builder()
                    .firstName("Ram")
                    .lastName("Sharma")
                    .build();
            employee.setLaptops(new ArrayList<Laptop>(){{

                add(Laptop.builder().brand("HP").type("Classic").build());
                add(Laptop.builder().brand("DELL").type("New").build());
            }});

            Employee employee1 =  Employee.builder()
                    .firstName("SHyam")
                    .lastName("Kumar")
                    .build();
            employee.setLaptops(new ArrayList<Laptop>(){{

                add(Laptop.builder().brand("MAC").type("APPLE").build());
                add(Laptop.builder().brand("ACER").type("AVG").build());
            }});

            entityManager.persist(employee);
            entityManager.persist(employee1);
        });
    }

    private static void printAll(){

    }

    private static void apply(Consumer<EntityManager> consumer){
        try{
            final EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();

            consumer.accept(em);

            em.getTransaction().commit();
            em.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
