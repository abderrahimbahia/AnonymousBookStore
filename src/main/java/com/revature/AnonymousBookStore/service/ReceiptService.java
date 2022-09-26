package com.revature.AnonymousBookStore.service;

import com.revature.AnonymousBookStore.entity.Receipt;
import com.revature.AnonymousBookStore.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    CartService cartService;

    public void add(Receipt receipt){
        receiptRepository.save(receipt);
    }

    public Receipt getById(Long id){
        return receiptRepository.findById(id).get();
    }

    public List<Receipt> getAll(){
        return receiptRepository.findAll();
    }

}
