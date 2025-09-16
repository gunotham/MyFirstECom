package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    final private OrderService orderService;
    
    OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<String> executeOrder(@PathVariable Long userId){
        return new ResponseEntity<>(orderService.executeOrder(userId), HttpStatus.OK);
    }
    
    
}
