package com.revature.AnonymousBookStore.service;

import com.revature.AnonymousBookStore.entity.OrderLine;
import com.revature.AnonymousBookStore.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;


    public OrderLine getById(long id){
        return this.orderLineRepository.findById(id).get();
    }
    public List<OrderLine> getAll(){
        List<OrderLine> orderLines =  orderLineRepository.findAll();
        return  orderLines;
    }

    public OrderLine add(OrderLine orderLine){
        return this.orderLineRepository.save(orderLine);
    }

    public OrderLine getOrderLineByCartIDAndBookID(long cartId, long bookId){
        return this.orderLineRepository.findOrderLineByCartIDAndBookID(bookId, cartId);
    }

}
