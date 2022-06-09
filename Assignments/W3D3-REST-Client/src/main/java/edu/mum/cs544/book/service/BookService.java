package edu.mum.cs544.book.service;

import edu.mum.cs544.book.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    List<Book> getAll();

    void add(Book book);

    void update(Book book);

    void delete(int id);

    Book get(int id);

}
