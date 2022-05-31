package edu.miu.cs54;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class App {

    public static EntityManagerFactory emf;

    public static void main(String[] args) {

        emf= Persistence.createEntityManagerFactory("cs544");

        Appointment appointment = Appointment.builder()
                .appdate("2021/01/01")
                .patient(Patient.builder()
                        .name("SUTI")
                        .city("FAIRFIELD")
                        .street("100 4th")
                        .zip("52557")
                        .build())
                .payment(Payment.builder()
                        .amount(500.56)
                        .paydate("2021/02/01")
                        .build())
                .doctor(Doctor.builder()
                        .doctortype("GYNAE")
                        .firstname("TULKE ")
                        .lastname("TIWARI")
                        .build())
                .build();

        apply(entityManager -> {
            entityManager.persist(appointment);
        });

    }

    public static void apply(Consumer<EntityManager> consumer){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        consumer.accept(em);

        em.getTransaction().commit();
        em.close();
    }
}
