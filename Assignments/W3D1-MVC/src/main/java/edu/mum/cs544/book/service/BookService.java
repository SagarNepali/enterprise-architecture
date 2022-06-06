package edu.mum.cs544.book.service;

import edu.mum.cs544.book.dao.BookDao;
import edu.mum.cs544.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    BookDao bookDao;

    public List<Book> findAll(){
        return bookDao.findAll();
    }

    public Book findOne(Integer id){
        return bookDao.getOne(id);
    }

    public void update(Book book){
        bookDao.save(book);
    }

    public void save(Book book){
        bookDao.save(book);
    }

    public void deleteById(Integer id){
        bookDao.delete(findOne(id));
    }
}
