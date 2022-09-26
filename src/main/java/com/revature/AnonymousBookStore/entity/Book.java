package com.revature.AnonymousBookStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor

public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long    bookID;

    @Column(unique = true, nullable = false)
    private String  ISBN;

    @Column(nullable = false)
    private String  title;

    @Column(nullable = false)
    private String  author;

    private String  category;

    @Column(nullable = false)
    private int    quantity;

    @Column(nullable = false)
    private double  price;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderLine> OrderLines;

}

