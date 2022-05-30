package edu.mum.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class AppBook {

    private static EntityManagerFactory emf;

    public static void main(){
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Book book = Book.builder()
                        .author("Bipin")
                        .ISBN("12345")
                .publishDate(new Date())
                .title("LAYZ")
                    .build();

        em.persist(book);

        Book book2 = Book.builder()
                .author("Suti")
                .ISBN("123123")
                .publishDate(new Date())
                .title("PLAYZ")
                .build();

        em.persist(book2);

        em.persist(Book.builder().author("ME").publishDate(new Date()).ISBN("111").price(22.99).title("EATZ").build());

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all cars
        TypedQuery<Book> query = em.createQuery("from Book",Book.class);
        List<Book> bookList = query.getResultList();
        print(bookList);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Book> query1 = em.createQuery("from Book",Book.class);
        List<Book> books = query1.getResultList();
        for (Book b : books) {
            if(b.getTitle().equals("LAYZ")){
                b.setTitle("EASY EATS");
            }
            if(b.getAuthor().equals("ME")){
                em.remove(b);
            }
        }
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Book> query2 = em.createQuery("from Book",Book.class);
        List<Book> bookList1 = query2.getResultList();
        print(bookList1);
        em.getTransaction().commit();
        em.close();
    }


    static void print(List<Book> bookList){
        for (Book b : bookList) {
            System.out.println("title= " + b.getTitle() + ", ISBN= "
                    + b.getISBN() + ", author= " + b.getAuthor()
                    + ", publish_date: "+b.getPublishDate()+", price: "+b.getPrice());
        }
    }
}
