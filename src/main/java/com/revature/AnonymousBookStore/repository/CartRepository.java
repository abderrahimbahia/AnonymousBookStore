package com.revature.AnonymousBookStore.repository;

import com.revature.AnonymousBookStore.entity.Cart;
import com.revature.AnonymousBookStore.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart c WHERE c.customerid_fk = ?!", nativeQuery = true)
    Cart findByCustomerID(Long id);

    //get all order lines that has the cart id
    @Query(value = "SELECT * FROM order_line ol WHERE ol.cartid_fk = ?1", nativeQuery = true)
    List<OrderLine> getOrderLines(long id);
}
