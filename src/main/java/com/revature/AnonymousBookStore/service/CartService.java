package com.revature.AnonymousBookStore.service;

import com.revature.AnonymousBookStore.entity.Book;
import com.revature.AnonymousBookStore.entity.Cart;
import com.revature.AnonymousBookStore.exception.BookOutOfStockException;
import com.revature.AnonymousBookStore.exception.CartDoesNotExistException;
import com.revature.AnonymousBookStore.repository.BookRepository;
import com.revature.AnonymousBookStore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService   bookService;

    public void add(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getById(Long id) throws Exception{
        Cart cart = cartRepository.findById(id).get();
        if(cart.equals(null))
            throw new CartDoesNotExistException("Cart doesn't exist!");
        return cart;
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart addToCart(Cart cart, Long bookId, int quantity) throws Exception{
        Book book = bookService.getById(bookId);
        if(book.equals(null))
            throw new BookOutOfStockException("Book doesn't exist!");

        if(book.getQuantity() < quantity)
            throw new BookOutOfStockException("The Quantity needed is not available, current book quantity is"+ book.getQuantity());
        else{
            book.setQuantity(book.getQuantity() - quantity);
            bookRepository.save(book);

            cart.addToCart(book, quantity);
            cartRepository.save(cart);
        }

        return cart;
    }

    public Cart removeFromCart(Cart cart, Long bookId, int quantity) throws Exception{
        Book book = bookService.getById(bookId);
        cart.removeFromCart(book, quantity);
        cartRepository.save(cart);

        return cart;
    }
}
