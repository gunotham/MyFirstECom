package com.extron.MyFirstECom.Repository;

import com.extron.MyFirstECom.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndProductId(Long cartId, Long productId);
    
    @Query("SELECT SUM(ci.product.price * ci.quantity) FROM CartItem ci WHERE ci.cart.id = :cartId")
    BigDecimal sumOfAllProductPriceWithCartId(@Param("cartId") Long cartId);
    
    void deleteByCartIdAndProductId(Long cartId, Long productId);
}
