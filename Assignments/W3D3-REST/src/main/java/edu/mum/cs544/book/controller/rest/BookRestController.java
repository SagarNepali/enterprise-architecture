package edu.mum.cs544.book.controller.rest;

import edu.mum.cs544.book.domain.Book;
import edu.mum.cs544.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookService bookService;

    @Autowired
    BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id){
        return bookService.findOne(id);
    }

    @PostMapping
    public RedirectView add(@RequestBody Book book){
        bookService.save(book);
        return new RedirectView("/api/books/"+book.getId());
    }

    @PostMapping("/{id}")
    public void update(@RequestBody Book book,@PathVariable int id){
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        bookService.deleteById(id);
    }

}
