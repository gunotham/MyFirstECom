package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
}
