package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.*;
import com.extron.MyFirstECom.Repository.CartItemRepo;
import com.extron.MyFirstECom.Repository.CartRepo;
import com.extron.MyFirstECom.Repository.ProductRepo;
import com.extron.MyFirstECom.Repository.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CartService {
    
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;

    public List<CartItem> getCartItems(Long userId) {
        Users user = userRepo.findById(userId).orElse(null);
        Cart cart = cartRepo.findByUserId(userId);
        
        return cart.getItems();
    }

    public String addCartItems(Long userId, Long prodId, Integer quantity) {
        
        Users user = userRepo.findById(userId).orElse(null);
        
        if(user == null){
            throw new RuntimeException("User not found with ID"+ userId);
        }
        
        Product product = productRepo.findById(prodId).orElse(null);
        if(product == null){
            throw new RuntimeException("Product not found");
        }
        
        if(quantity<=0){
            throw new RuntimeException("quantity must be greater than 0");
        }
        
        
        if (product.getStockQuantity()<quantity){
            throw new RuntimeException("Stock quantity is less than "+quantity+" available quantity "+ product.getStockQuantity());
        }
        else{
            product.setStockQuantity(product.getStockQuantity() - quantity);
        }
        
        Cart cart = cartRepo.findByUserId(userId);        
        
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        
        cartItemRepo.save(newItem);
        
        return "added to cart!!";
    }
}
