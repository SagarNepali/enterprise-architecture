package edu.mum.cs544.book.dao;

import edu.mum.cs544.book.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("from Book b").getResultList();
    }

    @Override
    public Book getById(Integer id) {
        return entityManager.find(Book.class,id);
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(getById(id));
    }
}
