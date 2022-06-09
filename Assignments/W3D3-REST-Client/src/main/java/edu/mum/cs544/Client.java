package edu.mum.cs544;

import edu.mum.cs544.book.domain.Book;
import edu.mum.cs544.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Client implements CommandLineRunner {

    @Autowired
    BookService bookService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(bookService.getAll());
        Book b = new Book(null,"BABAJI"," 978-3-16-148410-0","Ma",200.99);
        bookService.add(b);
        b.setAuthor("ME");
        b.setId(1);
        bookService.update(b);
        bookService.delete(1);
        System.out.println(bookService.getAll());
        b = bookService.get(2);
        System.out.println(b);




    }
}
