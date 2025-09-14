package com.extron.MyFirstECom.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extron.MyFirstECom.DTO.AddToCartDTO;
import com.extron.MyFirstECom.Model.CartItem;
import com.extron.MyFirstECom.Service.CartService;
import com.extron.MyFirstECom.Service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/cart")
@Log4j2 
public class CartController {
    
    private CartService cartService;
    
    CartController( CartService cartService){
        this.cartService = cartService;
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId){

        return new ResponseEntity<>(cartService.getCartItems(userId), HttpStatus.OK);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<String> addCartItems(@PathVariable Long userId,
                                               @RequestBody AddToCartDTO req){
        try{
            cartService.addCartItems(userId, req.getProdId(), req.getQuantity());
            return new ResponseEntity<>("Product added to the cart successfully", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
