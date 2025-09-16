package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
}
