package edu.mum.cs544.book.dao;

import edu.mum.cs544.book.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(Integer id);

    void update(Book book);

    void save(Book book);

    void delete(Integer id);


}
