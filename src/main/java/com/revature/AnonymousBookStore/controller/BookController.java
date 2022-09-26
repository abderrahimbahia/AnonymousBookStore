package com.revature.AnonymousBookStore.controller;

import com.revature.AnonymousBookStore.entity.Book;
import com.revature.AnonymousBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class  BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public Book insert(@RequestBody Book book) {
        bookService.add(book);
        return book;
    }

    @GetMapping()
    public List<Book> getById() {
        return bookService.getAll();
    }

    @GetMapping("/findById/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/findByIsbnNumber/{isbn}")
    public Book getByISBN(@PathVariable String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @GetMapping("/findByKeyWord/{keyWord}")
    public List<Book> getByKeyWord(@PathVariable String keyWord) {
        return bookService.getByKeyWord(keyWord);
    }
}
