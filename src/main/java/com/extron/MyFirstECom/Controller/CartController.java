package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.DTO.AddToCartDTO;
import com.extron.MyFirstECom.Model.CartItem;
import com.extron.MyFirstECom.Service.CartService;
import com.extron.MyFirstECom.Service.MyUserDetailService;
import com.extron.MyFirstECom.Service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Log4j2
public class CartController {
    
    private UserService userService;
    private CartService cartService;
    
    CartController(UserService userService, CartService cartService){
        this.userService = userService;
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
