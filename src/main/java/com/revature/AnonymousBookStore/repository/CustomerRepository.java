package com.revature.AnonymousBookStore.repository;

import com.revature.AnonymousBookStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer c WHERE c.username = ?1", nativeQuery = true)
    Customer findByUsername(String username);
}
