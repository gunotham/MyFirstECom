package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.Model.Product;
import com.extron.MyFirstECom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService prodService;

    @GetMapping("/")
    public String heyWorld(){
        return "Test process";
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody Product prod){
        prodService.addProduct(prod);
        return new ResponseEntity<>("Successfully added data", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(prodService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/product/{Id}")
    public ResponseEntity<String> updateProductById(@PathVariable int Id, @RequestBody Product prod){
        Product product = prodService.updateProductById(Id, prod);
        if(product != null){
            return new ResponseEntity<>("Successfully updated the Product", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

}
