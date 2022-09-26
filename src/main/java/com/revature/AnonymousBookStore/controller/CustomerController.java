package com.revature.AnonymousBookStore.controller;

import com.revature.AnonymousBookStore.entity.Cart;
import com.revature.AnonymousBookStore.entity.Customer;
import com.revature.AnonymousBookStore.exception.CartDoesNotExistException;
import com.revature.AnonymousBookStore.exception.InvalidCustomerFormatException;
import com.revature.AnonymousBookStore.exception.InvalidPasswordException;
import com.revature.AnonymousBookStore.exception.UsernameNotFoundException;
import com.revature.AnonymousBookStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
//@Scope("session")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/viewprofile/{id}")
    public Customer display(@PathVariable long id) throws Exception{
        return customerService.getById(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customer) throws Exception{
        Customer current = customerService.getByUsername(customer.getUsername());
        if(current == null)
            throw new UsernameNotFoundException("No user with matching username available!");
        else
            if(!current.getPassword().equals(customer.getPassword()))
                throw  new InvalidPasswordException("Invalid password!");
            return "Logged in successfully!";
    }

    @PostMapping("/register")
    public Customer register(@RequestBody Customer newCustomer) throws Exception{

        if(newCustomer == null)
            throw new InvalidCustomerFormatException("Please provide all necessary data!");

        if(customerService.usernameTaken(newCustomer.getUsername())) {
            throw new UsernameNotFoundException("User name is taken!");
        }
        return customerService.addCustomer(newCustomer);
    }

    @PutMapping("/update/{id}")
    public  Customer update(@PathVariable("id") long id, @RequestBody Customer customer) throws Exception{
        Optional<Customer> customerToUpdateOptional = Optional.ofNullable(this.customerService.getById(id));
        if(!customerToUpdateOptional.isPresent()){
            return null;
        }

        Customer customerToUpdate = customerToUpdateOptional.get();

        if(customer.getFirstName() != null){
            customerToUpdate.setFirstName(customer.getFirstName());
        }

        if(customer.getLastName() != null){
            customerToUpdate.setLastName(customer.getLastName());
        }

        if(customer.getUsername() != null){
            if(customerService.usernameTaken(customer.getUsername())) {
                throw new UsernameNotFoundException("User name is taken!");
            }
            customerToUpdate.setUsername(customer.getUsername());
        }

        if(customer.getPassword() != null){
            customerToUpdate.setPassword(customer.getPassword());
        }

        if(customer.getEmail() != null){
            customerToUpdate.setEmail(customer.getEmail());
        }

        if(customer.getStreet() != null){
            customerToUpdate.setStreet(customer.getStreet());
        }

        if(customer.getCity() != null){
            customerToUpdate.setCity(customer.getCity());
        }

        if(customer.getStreet() != null){
            customerToUpdate.setStreet(customer.getState());
        }

        if(customer.getZipcode() != 0){
            customerToUpdate.setZipcode(customer.getZipcode());
        }

        if(customer.getCardNumber() != null){
            customerToUpdate.setCardNumber(customer.getCardNumber());
        }

        return this.customerService.updateCustomer(customerToUpdate);
    }

    @GetMapping("/viewCustomercart/customerId/{customerId}")
    public Cart displayCustomerCart(@PathVariable("customerId") long customerId) throws Exception{
        Cart cart =  customerService.getCustomerCart(customerId);
        if(cart.equals(null))
            throw new CartDoesNotExistException("Employee doesn't have any shopping carts");
        return cart;
    }

    @PatchMapping("addToCustomerCart/customerId/{customerId}/books/{bookId}/quantity/{quantity}")
    public Cart addToCart(@PathVariable("customerId") Long customerId, @PathVariable("bookId") Long bookId, @PathVariable int quantity) throws Exception{
        // if our quantity is null, default to 0:
        if(quantity == 0) {
            quantity = 1;
        }
        // return the updated cart:
        return customerService.addToCart(customerId, bookId, quantity);
    }

    @PatchMapping("removeFromCart/customerId/{customerId}/books/{bookId}/quantity/{quantity}")
    public Cart removeFromCart(@PathVariable("customerId") Long customerId, @PathVariable("bookId") Long bookId, @PathVariable int quantity) throws Exception{
        // if our quantity is null, default to 0:
        if(quantity == 0) {
            quantity = 1;
        }
        // return the updated cart:
        return customerService.removeFromCart(customerId, bookId, quantity);
    }

    @GetMapping("/ViewPreviousOrders/username/{username}")
    public List<Cart> viewPreviousCarts(@PathVariable String username) throws Exception{
        return customerService.viewPreviousOrders(username);
    }

    @PatchMapping("checkout/{customerId}")
    public void checkout(@PathVariable("customerId") Long customerId) throws Exception{
        customerService.checkout(customerId);
    }

}
