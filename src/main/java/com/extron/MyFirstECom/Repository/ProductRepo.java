package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
