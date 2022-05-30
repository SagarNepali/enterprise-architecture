package com.mum.cs544;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        entityManagerFactory = Persistence.createEntityManagerFactory("cs544");

        partA();

        partB();

        partC();


    }

    private static void partC() {

        createSchool();
        printSchool();
    }

    private static void printSchool() {
        apply(em -> {
            TypedQuery<School> query = em.createQuery("from School ",School.class);
            List<School> schools = query.getResultList();

            schools.forEach(e -> {
                System.out.println("school= "+e.getName()+", " +
                        "student="+e.getStudents());
            });


        })

        ;
    }

    private static void createSchool() {

        apply(em -> {
            School school = School.builder()
                    .name("ABS")
                    .students(new HashMap<>(){{
                        put(1L,Student.builder().firstName("ABS FIRST").lastName("ABS LAST").build());
                        put(2L,Student.builder().firstName("ABS 2 FIRST").lastName("ABS 2 LAST").build());
                    }})
                    .build();

            School school1 = School.builder()
                    .name("KISM")
                    .students(new HashMap<>(){{
                        put(3L,Student.builder().firstName(" FIRST").lastName("LAST").build());
                        put(4L,Student.builder().firstName("2 FIRST").lastName("LAST").build());
                    }})
                    .build();

            em.persist(school);
            em.persist(school1);
        });

    }

    private static void partA(){
        insertEmployees();
        printAll();
    }

    private static void partB(){
        insertPassengers();
        printPassengers();
    }

    private static void printPassengers() {
        apply(em -> {
            TypedQuery<Passenger> query = em.createQuery("from Passenger ",Passenger.class);
            List<Passenger> passengers = query.getResultList();

            passengers.forEach(e -> {
                System.out.println("firstname= "+e.getName()+", " +
                        "flights="+e.getFlights().stream().map(l -> l.getFrom()+" -> "+l.getTo()).collect(Collectors.joining(",")));
            });


        })

        ;

    }

    private static void insertPassengers() {

        apply(em -> {
            Passenger p = Passenger.builder()
                    .name("HARI")
                    .flights(new ArrayList<>(){{
                        add(Flight.builder().flightNumber(1).from("TIA").to("LA").date(new Date()).build());
                        add(Flight.builder().flightNumber(1).from("LA").to("CHG").date(new Date()).build());
                        add(Flight.builder().flightNumber(11).from("CHG").to("CDR").date(new Date()).build());
                    }})
                    .build();

            Passenger p1 = Passenger.builder()
                    .name("TRISH")
                    .flights(new ArrayList<>(){{
                        add(Flight.builder().flightNumber(1).from("NEV").to("BVD").date(new Date()).build());
                        add(Flight.builder().flightNumber(11).from("BVD").to("BKR").date(new Date()).build());
                    }})
                    .build();

            em.persist(p);
            em.persist(p1);
        });

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
            employee1.setLaptops(new ArrayList<Laptop>(){{

                add(Laptop.builder().brand("MAC").type("APPLE").build());
                add(Laptop.builder().brand("ACER").type("AVG").build());
            }});

            entityManager.persist(employee);
            entityManager.persist(employee1);
        });
    }

    private static void printAll(){

        apply(em -> {
            TypedQuery<Employee> query = em.createQuery("from Employee ",Employee.class);
            List<Employee> employees = query.getResultList();

            employees.forEach(e -> {
                System.out.println("firstname= "+e.getFirstName()+", " +
                        "laptops="+e.getLaptops().stream().map(l -> l.getBrand()).collect(Collectors.joining(",")));
            });


        })

        ;

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
