package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findAllByUserId(Long userId);
}
