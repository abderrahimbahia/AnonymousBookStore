package com.revature.AnonymousBookStore.service;

import com.revature.AnonymousBookStore.entity.Cart;
import com.revature.AnonymousBookStore.entity.Customer;
import com.revature.AnonymousBookStore.exception.CartDoesNotExistException;
import com.revature.AnonymousBookStore.exception.CustomerDoesNottExistException;
import com.revature.AnonymousBookStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartService cartService;

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getById(long id) throws Exception{
        Customer customer = customerRepository.findById(id).get();
        if(customer.equals(null))
            throw new CustomerDoesNottExistException("Customer with corresponding id is not available!");
        return customer;
    }

    public Customer getByUsername(String username){
        return customerRepository.findByUsername(username);
    }

    public  Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public boolean usernameTaken(String username){
        boolean isTaken = false;
        List<Customer> customers = customerRepository.findAll();
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getUsername().equals(username)) {
                isTaken = true;
                break;
            }
        }
        return isTaken;
    }

    public Cart getCustomerCart(long customerId) throws Exception{
        Customer customer = getById(customerId);
        Cart cart = customer.getCurrent();
        return cart;
    }

    public Cart addToCart(Long customerId, Long bookId, int quantity) throws Exception{
        Customer customer = getById(customerId);
        Cart cart = customer.getCurrent();

        cartService.addToCart(cart, bookId, quantity);
        return cart;
    }

    public Cart removeFromCart(Long customerId, Long bookId, int quantity) throws Exception{
        Customer customer = getById(customerId);
        Cart cart = customer.getCurrent();

        cartService.removeFromCart(cart, bookId, quantity);
        return cart;
    }

    public List<Cart> viewPreviousOrders(String username) throws Exception{
        Customer customer = customerRepository.findByUsername(username);
        List<Cart> previousCarts = customer.getPrevious();
        if(previousCarts.isEmpty())
            throw new CartDoesNotExistException("customer doesn't have any previous orders!");
        return previousCarts;
    }

    public void checkout(Long customerId) throws Exception{
        Customer customer = getById((customerId));
        customer.getPrevious().add(customer.getCurrent());
        customer.setCurrent(new Cart());
        customerRepository.save(customer);
    }
}