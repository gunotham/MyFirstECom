package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Product;
import com.extron.MyFirstECom.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo prodRepo;

    public ProductService(ProductRepo prodRepo){
        this.prodRepo = prodRepo;
    }

    public List<Product> getAllProducts(){
        return prodRepo.findAll();
    }
}
