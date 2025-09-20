package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.*;
import com.extron.MyFirstECom.Repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    
    final private CartRepo cartRepo;
    final private UserRepo userRepo;
    final private CartItemRepo cartItemRepo;
    final private OrderRepo orderRepo;
    final private OrderItemRepo orderItemRepo;

    public OrderService(CartRepo cartRepo, UserRepo userRepo, CartItemRepo cartItemRepo, OrderRepo orderRepo, OrderItemRepo orderItemRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.cartItemRepo = cartItemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }

    @Transactional
    public String executeOrder(Long userId) {
        Users user = userRepo.findById(userId).orElse(null);
    
        if (user == null) {
            throw new RuntimeException("User with this id does not exist");
        }
        
        Cart cart = cartRepo.findByUserId(userId);
        List<CartItem> cartItemList = cart.getItems();
        if(cartItemList.isEmpty()){
            return "Cart is Empty";
        }
        
        Order order  = new Order();
        
        List<OrderItem> orderItemList = new ArrayList<>();
        BigDecimal orderPrice = BigDecimal.valueOf(0);
        for(CartItem cartItem:cartItemList){
            if (cartItem.getQuantity() > cartItem.getProduct().getStockQuantity()){
                return "Not enough quantity in stock!! Decrease your order quantity";
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItemList.add(orderItem);
            cartItem.getProduct().setStockQuantity(
                    cartItem.getProduct().getStockQuantity() - 
                    cartItem.getQuantity());
            
            BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
            BigDecimal price = cartItem.getProduct().getPrice();
            orderPrice = orderPrice.add(price.multiply(quantity));
        }
        
        order.setOrderItems(orderItemList);
        order.setOrderedAt(new Date());
        order.setUser(user);
        order.setAmount(orderPrice);
        orderRepo.save(order);
        
        cart.getItems().clear();
        cartRepo.save(cart);
        
        return ("Order has been placed, the total amount is "+ order.getAmount()+ " and was ordered at "+ order.getOrderedAt());
    }
}
