package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.Model.Product;
import com.extron.MyFirstECom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(prodService.getAllProducts(), HttpStatus.OK);
    }
}
