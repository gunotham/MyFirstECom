package com.extron.MyFirstECom.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "prodId")
    private Product prod;
    
    private int quantity;
}
