package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Product;
import com.extron.MyFirstECom.Repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo prodRepo;

    public ProductService(ProductRepo prodRepo){
        this.prodRepo = prodRepo;
    }

    public List<Product> getAllProducts(){
        return prodRepo.findAll();
    }

    public void addProduct(Product prod) {
        prodRepo.save(prod);
    }

    public Product updateProductById(int Id, Product prod) {
        prod.setProd_id(Id);
        return prodRepo.save(prod);
    }

    public boolean deleteProductById(int id) {
        Optional<Product> prod = prodRepo.findById(id);

        if(prod.isPresent()){
            prodRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Product getProductById(int id) {
        return prodRepo.findById(id).orElse(null);
    }
}
