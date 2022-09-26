package com.revature.AnonymousBookStore.service;

import com.revature.AnonymousBookStore.entity.Book;
import com.revature.AnonymousBookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void add(Book book) {
        bookRepository.save(book);
    }

    public Book updateBookQuantity(Book book, int newQuantity){
        book.setQuantity(newQuantity);
        bookRepository.save(book);
        return book;
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book getByIsbn(String ISBN) {
        return bookRepository.findBookByISBN(ISBN);
    }

    public List<Book> getByKeyWord(String keyWord){
        return bookRepository.findByTitleContaining(keyWord);
    }

    public List<Book> getAll()
    {
        return bookRepository.findAll();
    }
}
