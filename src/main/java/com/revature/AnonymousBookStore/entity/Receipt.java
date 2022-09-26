package com.revature.AnonymousBookStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor

public class Receipt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long    receiptID;
    private Date    date;
    private double  total;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Cart cart;


}
