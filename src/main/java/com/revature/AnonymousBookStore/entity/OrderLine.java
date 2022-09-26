package com.revature.AnonymousBookStore.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor

public class OrderLine{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Cart cart;

    private int quantity;
    public OrderLine(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.quantity * this.book.getPrice();
    }
}
