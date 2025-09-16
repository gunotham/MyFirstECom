package com.extron.MyFirstECom.Model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    
    private Date orderedAt;
    private Float amount;
    
}
