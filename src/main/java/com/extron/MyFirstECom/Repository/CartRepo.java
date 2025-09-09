package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUserUserId(Long userId);
}
