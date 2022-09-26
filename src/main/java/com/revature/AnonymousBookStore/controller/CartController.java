package com.revature.AnonymousBookStore.controller;

import com.revature.AnonymousBookStore.entity.Cart;
import com.revature.AnonymousBookStore.exception.CartDoesNotExistException;
import com.revature.AnonymousBookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/viewcart")
    public List<Cart> display() throws Exception{
        List<Cart> carts =  cartService.getAll();
        if(carts.isEmpty())
            throw new CartDoesNotExistException("Employee doesn't have any shopping carts");
        return carts;
    }

    @GetMapping("/getByID/{id}")
    public Cart getById(@PathVariable("id") Long id) throws Exception {
        Cart cart = cartService.getById(id);
        if(!cart.equals(null))
            throw new CartDoesNotExistException("Cart with " + id + " doesn't exist!");
        return cartService.getById(id);
    }

    @PostMapping("/addCart")
    public Cart add(@RequestBody Cart cart) throws Exception{
        if(cart == null)
            throw new CartDoesNotExistException("Please provide all necessary data for a shopping cart");
        this.cartService.add(cart);
        return cart;
    }

    @PatchMapping("/addToCart/cartId/{cartId}/bookId/{bookId}/Quantity/{quantity}")
    public Cart addToCart(@PathVariable("cartId") Long cartId, @PathVariable("bookId") Long bookId, @PathVariable("quantity") int quantity) throws Exception{
        Cart cart = cartService.getById(cartId);
        return cartService.addToCart(cart, bookId, quantity);
    }

    @PatchMapping("/removeFromCart/cartId/{cartId}/bookId/{bookId}/Quantity/{quantity}")
    public Cart removeFromCart(@PathVariable("cartId") Long cartId, @PathVariable("bookId") Long bookId, @PathVariable("quantity") int quantity) throws Exception{
        Cart cart = cartService.getById(cartId);
        return cartService.removeFromCart(cart, bookId, quantity);
    }
}
