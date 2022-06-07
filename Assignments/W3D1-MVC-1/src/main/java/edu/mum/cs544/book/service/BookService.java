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
        return bookDao.getAll();
    }

    public Book findOne(Integer id){
        return bookDao.get(id);
    }

    public void update(Book book){
        bookDao.update(book);
    }

    public void save(Book book){
        bookDao.add(book);
    }

    public void deleteById(Integer id){
        bookDao.delete(findOne(id));
    }
}
