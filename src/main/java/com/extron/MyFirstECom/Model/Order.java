package com.extron.MyFirstECom.Model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    
    private Date ordered_at;
    private Float order_amount;
    
}
