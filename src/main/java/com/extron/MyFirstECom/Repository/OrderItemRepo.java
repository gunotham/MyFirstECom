package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
    
    @Query("SELECT SUM(oi.price * oi.quantity) FROM OrderItem oi WHERE oi.order.id = :orderId")
    BigDecimal calculateTotalAmountByOrderId(@Param("orderId") Long orderId);
}
