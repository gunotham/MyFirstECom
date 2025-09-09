package com.extron.MyFirstECom.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prod_id;
    private String prod_name;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;
    
    private String description;
    private BigDecimal prod_price;
    private int stockQuantity;
    
}
