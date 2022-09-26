package com.revature.AnonymousBookStore.repository;

import com.revature.AnonymousBookStore.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    @Query(value = "SELECT * FROM order_line ol where ol.book_bookid = ?1 and ol.cart_cartid = ?2", nativeQuery = true)
    OrderLine findOrderLineByCartIDAndBookID(long bookID, long cartID);
}
