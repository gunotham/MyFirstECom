package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.DTO.OrderResponseDTO;
import com.extron.MyFirstECom.Model.Order;
import com.extron.MyFirstECom.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    final private OrderService orderService;
    
    OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllOrders(@PathVariable Long userId){
        List<OrderResponseDTO> orders = orderService.getAllOrders(userId);
        if(orders.isEmpty()){
            return new ResponseEntity<>("No previous Orders were made!!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> executeOrder(@PathVariable Long userId){
        return new ResponseEntity<>(orderService.executeOrder(userId), HttpStatus.OK);
    }    
}
