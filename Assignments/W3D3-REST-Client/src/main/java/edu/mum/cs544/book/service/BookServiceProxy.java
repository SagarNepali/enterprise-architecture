package edu.mum.cs544.book.service;

import edu.mum.cs544.book.domain.Book;
import edu.mum.cs544.constant.URIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceProxy implements BookService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Book> getAll() {
//        Object[] objs  = restTemplate.getForObject(URIConstants.BOOKS_URI, Object[].class);
//        return Arrays.stream(objs).map(obj ->(Book) obj).collect(Collectors.toList());

        ResponseEntity<List<Book>> response  = restTemplate.exchange(URIConstants.BOOKS_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>(){});
        return response.getBody();
    }

    @Override
    public void add(Book book) {
        restTemplate.postForObject(URIConstants.BOOKS_URI,book, Book.class);
    }

    @Override
    public void update(Book book) {
        restTemplate.put(URIConstants.BOOKS_URI_WITH_ID,book,book.getId());
    }

    @Override
    public void delete(int id) {
        restTemplate.delete(URIConstants.BOOKS_URI_WITH_ID,id);
    }

    @Override
    public Book get(int id) {
        return restTemplate.getForObject(URIConstants.BOOKS_URI_WITH_ID,Book.class,id);
    }
}
