package com.extron.MyFirstECom.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    @JsonBackReference
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "prodId")
    private Product prod;
    
    private int quantity;
}
