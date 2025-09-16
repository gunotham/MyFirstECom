package com.extron.MyFirstECom.Controller;

import com.extron.MyFirstECom.Model.Product;
import com.extron.MyFirstECom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService prodService;
    

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product prod){
        prodService.addProduct(prod);
        return new ResponseEntity<>("Successfully added data", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(prodService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        return new ResponseEntity<>(prodService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@PathVariable long id, @RequestBody Product prod){
        Product product = prodService.updateProductById(id, prod);
        if(product != null){
            return new ResponseEntity<>("Successfully updated the Product", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable long id){
        if(prodService.deleteProductById(id)){
            return new ResponseEntity<>("Successfully deleted Product", HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to delete data or invalid product Id", HttpStatus.BAD_REQUEST);
    }

}
