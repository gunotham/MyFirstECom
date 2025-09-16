package com.extron.MyFirstECom.Service;

import com.extron.MyFirstECom.Model.Category;
import com.extron.MyFirstECom.Model.Product;
import com.extron.MyFirstECom.Repository.CategoryRepo;
import com.extron.MyFirstECom.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    
    public String addNewCategory(Category category) {
               
        categoryRepo.save(category);
        return "Added a new category";
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public String deleteCategory(Long categoryId) {        
        List<Product> productList = productRepo.findAllByCategoryId(categoryId);
        
        if(productList.isEmpty()){
            categoryRepo.deleteById(categoryId);
            return "Category is deleted";
        }
        else
            return "Products exists in this category";
    }
    
    
    
    
}
