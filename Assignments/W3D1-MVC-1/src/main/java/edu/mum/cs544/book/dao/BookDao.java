package edu.mum.cs544.book.dao;

import edu.mum.cs544.Car;
import edu.mum.cs544.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao{

    List<Book> getAll();

    void add(Book book);

    Book get(int id);

    void update(Book book);

    void delete(Book book);

}
