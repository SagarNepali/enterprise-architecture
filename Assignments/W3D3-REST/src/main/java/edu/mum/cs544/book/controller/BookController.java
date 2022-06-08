package edu.mum.cs544.book.controller;

import edu.mum.cs544.book.domain.Book;
import edu.mum.cs544.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/books";
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/bookList";
    }

    @PostMapping
    public String add(@Valid Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.book",bindingResult);
            redirectAttributes.addFlashAttribute("book",book);
            return "redirect:/books/add";
        }else{
            bookService.save(book);
            return "redirect:/books";
        }

    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        if (!model.containsAttribute("book")) {
            model.addAttribute("book", new Book());
        }
        model.addAttribute("msg", "Add");
        return "book/bookDetail";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        if (!model.containsAttribute("book")) {
            model.addAttribute("book", new Book());
        }

        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("msg", "Update");
        return "book/bookDetail";
    }

    @PostMapping("/{id}")
    public String update(@Valid Book book, BindingResult bindingResult,RedirectAttributes redirectAttributes, @PathVariable Integer id,Model model) {
        if (!model.containsAttribute("book")) {
            model.addAttribute("book", new Book());
        }
        bookService.update(book); // book.id already set by binding
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String delete(Integer bookId) {
        bookService.deleteById(bookId);
        return "redirect:/books";
    }

}