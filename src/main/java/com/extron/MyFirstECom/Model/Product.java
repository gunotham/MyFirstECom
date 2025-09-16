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
    private long id;
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    
}
