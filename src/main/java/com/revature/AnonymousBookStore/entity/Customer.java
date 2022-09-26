package com.revature.AnonymousBookStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   customerID;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username = "";

    @Column(nullable = false)
    private String password = "";

    private String email;
    private String street;
    private String city;
    private String state;
    private int    zipcode;
    private String cardNumber;

    @OneToOne(targetEntity = Cart.class, cascade = CascadeType.ALL)
    private Cart current;

    @OneToMany(targetEntity = Cart.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerId_fk", referencedColumnName = "customerID")
    private List<Cart> previous;

//    @OneToMany(targetEntity = Receipt.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    private List<Receipt> orders;

    public Customer () {
        this.current = new Cart();
        this.previous = new ArrayList<>();
    }
}

