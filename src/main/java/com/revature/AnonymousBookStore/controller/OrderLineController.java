package com.revature.AnonymousBookStore.controller;

import com.revature.AnonymousBookStore.service.BookService;
import com.revature.AnonymousBookStore.service.CartService;
import com.revature.AnonymousBookStore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderLine")
public class OrderLineController {

    @Autowired
    OrderLineService orderLineService;

    @Autowired
    BookService bookService;

    @Autowired
    CartService cartService;

//    @GetMapping("/display")
//    public List<OrderLine> display() throws  Exception{
//        List<OrderLine> orderLines = this.orderLineService.getAllOrderLines();
//        if(orderLines.isEmpty())
//            throw new OrderLineDontExistException("Order Line is empty!");
//        return orderLines;
//    }
//
//    @PostMapping("/addCart")
//    public OrderLine addOrderLine(@RequestBody OrderLine orderLine) throws Exception{
//        OrderLine newOrderLine = orderLineService.addOrderLine(orderLine);
//        if(newOrderLine.equals(null))
//            throw new OrderLineDontExistException("Order line is null!");
//        if(orderLine.getQuantity() <= newOrderLine.getBook().getQuantity()){
//            this.bookService.updateBookQuantity(newOrderLine.getBook(),   newOrderLine.getBook().getQuantity() - newOrderLine.getQuantity());
//            this.cartService.updateCartTotal(newOrderLine.getCart().getCartID(), newOrderLine.getQuantity() * newOrderLine.getBook().getPrice());
//            return newOrderLine;
//
//        }else throw new LimitedBookSupplyException("The desired quantity is not available");
//    }
//
//    @PostMapping("/update")
//    public OrderLine updateOrderLine(@RequestBody OrderLine orderLine) throws Exception {
//        Optional<OrderLine> currentOptional = Optional.ofNullable(this.orderLineService.getOrderLineById(orderLine.getOrderLineId()));
//        if (!currentOptional.isPresent())
//            throw new OrderLineDontExistException("Order line empty!");
//
//        OrderLine current = currentOptional.get();
//        if(current.getCart().getCartID() == orderLine.getCart().getCartID()){
//            if(current.getBook().getBookID() == orderLine.getBook().getBookID()) {
//                if(orderLine.getQuantity() <= current.getBook().getQuantity()){
//                    //updating the quantity in order line
//                    current.setQuantity(current.getQuantity() + orderLine.getQuantity());
//                    //update quantity in book table
//                    this.bookService.updateBookQuantity(orderLine.getBook(),   orderLine.getBook().getQuantity() - orderLine.getQuantity());
//                    //update the total in the cart
//                    this.cartService.updateCartTotal(orderLine.getCart().getCartID(), orderLine.getQuantity() * orderLine.getBook().getPrice());
//                    //save the new or updated order line
//                    this.orderLineService.addOrderLine(current);
//
//                    return current;
//                }
//                else throw new LimitedBookSupplyException("The desired quantity is not available");
//            }
//            else throw new OrderLineDontExistException("same cart, different book");
//        }
//        else throw new OrderLineDontExistException("same cart, different book");
//    }
}
