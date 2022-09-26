package com.revature.AnonymousBookStore.entity;

import com.revature.AnonymousBookStore.exception.BookOutOfStockException;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity

public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    cartID;

    @Column(nullable = false)
    //total amount
    private double  total;

    @Column(nullable = false)
    private boolean state = false;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> booksInCart;

    public Cart() {
        this.booksInCart = new ArrayList<>();
    }

    public void addToCart(Book newBook, int quantity) {
        for(int i = 0; i < booksInCart.size(); i ++) {
            OrderLine current = booksInCart.get(i);
            if(current.getBook().getBookID() == newBook.getBookID()) {
                current.setQuantity(current.getQuantity() + quantity);
                booksInCart.set(i, current);
                return;
            }
        }
        booksInCart.add(new OrderLine(newBook, quantity));
    }

    public void removeFromCart(Book newBook, int quantity) throws Exception{
        for(int i = 0; i < booksInCart.size(); i ++) {
            OrderLine current = booksInCart.get(i);
            if(current.getBook().getBookID() == newBook.getBookID()) {
                int newQuantity = current.getQuantity() - quantity;
                if(newQuantity <= 0)
                    booksInCart.remove(current);
                else {
                    current.setQuantity(newQuantity);
                    booksInCart.set(i, current);
                }
                return;
            }
            throw new BookOutOfStockException("The book is not in the cart");
        }
    }
}
