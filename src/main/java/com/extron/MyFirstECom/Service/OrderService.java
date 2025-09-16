package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Cart;
import com.extron.MyFirstECom.Model.Order;
import com.extron.MyFirstECom.Model.Users;
import com.extron.MyFirstECom.Repository.CartItemRepo;
import com.extron.MyFirstECom.Repository.CartRepo;
import com.extron.MyFirstECom.Repository.OrderRepo;
import com.extron.MyFirstECom.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    
    final private CartRepo cartRepo;
    final private UserRepo userRepo;
    final private CartItemRepo cartItemRepo;
    final private OrderRepo orderRepo;

    public OrderService(CartRepo cartRepo, UserRepo userRepo, CartItemRepo cartItemRepo, OrderRepo orderRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.cartItemRepo = cartItemRepo;
        this.orderRepo = orderRepo;
    }

    public String executeOrder(Long userId) {
        Users user = userRepo.findById(userId).orElse(null);
    
        if (user == null) {
            throw new RuntimeException("User with this id does not exist");
        }
        
        Cart cart = cartRepo.findByUserId(userId);
        Order order  = new Order();
        if(cart == null){
            return "Cart is Empty";
        }
        else{
            order.setUser(user);
            order.setOrderedAt(new Date());
            order.setAmount(cartItemRepo.sumOfAllProductPriceWithCartId(cart.getId()));
            System.out.println(user.getUsername());
        }
        orderRepo.save(order);
        
        return ("Order has been placed, the total amount is "+ order.getAmount()+ " and was ordered at "+ order.getOrderedAt());
    }
}
