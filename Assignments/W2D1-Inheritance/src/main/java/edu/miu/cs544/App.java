package edu.miu.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("cs544");
        partA();
    }

    private static void partA() {
        insert();
        print();
    }

    private static void insert() {


        Product p = Product.builder().name("MALPA").description("BAKERY").build();
        Product p2 = Product.builder().name("LOLWA").description("MISC").build();
        Product p3 = Product.builder().name("JEANS").description("MENS").build();
        Product p4 = Product.builder().name("PANTS").description("WOMENS").build();
        Product p5 = Product.builder().name("BOOTS").description("KIDS").build();
        Product p6 = Product.builder().name("SHOES").description("MENS").build();
        Product p7 = Product.builder().name("BOTTLE").description("HOME").build();
        Product cd = new CD("beyonce","MUSIC","POP ARTIST");
        Product dvd = new DVD("Comedy","WELCOME","Majnu Bhai");
        Product book = new Book("Night Owl","RANDOM","RANDOM");

        Customer customer = Customer.builder()
                .firstname("SUTO")
                .lastname("TITO").build();

        Customer customer2 = Customer.builder()
                .firstname("SUTI")
                .lastname("TITI").build();


        List<OrderLine> customerOrderLine = new ArrayList<>(){{
            add(OrderLine.builder().quantity(2).product(p).build());
            add(OrderLine.builder().quantity(22).product(p2).build());
            add(OrderLine.builder().quantity(22).product(dvd).build());
        }} ;

        List<OrderLine> customer1OrderLine = new ArrayList<>(){{
            add(OrderLine.builder().quantity(21).product(p7).build());
            add(OrderLine.builder().quantity(4).product(p3).build());
            add(OrderLine.builder().quantity(22).product(cd).build());
        }} ;

        List<OrderLine> customer2OrderLine = new ArrayList<>(){{
            add(OrderLine.builder().quantity(200).product(p).build());
            add(OrderLine.builder().quantity(4).product(p2).build());
            add(OrderLine.builder().quantity(42).product(p3).build());
            add(OrderLine.builder().quantity(22).product(book).build());
        }} ;

        List<OrderLine> customer2OrderLine2 = new ArrayList<>(){{
            add(OrderLine.builder().quantity(244).product(p5).build());
            add(OrderLine.builder().quantity(222).product(p4).build());
        }} ;


        List<OrderLine> customer2OrderLine3 = new ArrayList<>(){{
            add(OrderLine.builder().quantity(1).product(p6).build());
        }} ;



        List<Order> customer1orderList = new ArrayList<>() {{
           add(Order.builder().customer(customer).orderLines(customerOrderLine).date(new Date()).build());
           add(Order.builder().customer(customer).orderLines(customer1OrderLine).date(new Date()).build());
        }};



        List<Order> customer2orderList = new ArrayList<>() {{
            add(Order.builder().customer(customer2).orderLines(customer2OrderLine).date(new Date()).build());
            add(Order.builder().customer(customer2).orderLines(customer2OrderLine2).date(new Date()).build());
            add(Order.builder().customer(customer2).orderLines(customer2OrderLine3).date(new Date()).build());
        }};

        customer.setOrders(customer1orderList);
        customer2.setOrders(customer2orderList);

        apply(entityManager -> {
            entityManager.persist(customer);
            entityManager.persist(customer2);

        });
    }

    private static void print() {

        apply(entityManager -> {

            List<Customer> customers = entityManager.createQuery("from Customer", Customer.class).getResultList();

            customers.forEach(customer -> System.out.println("firstname= "+customer.getFirstname()+", lastname="+customer.getLastname()));

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

