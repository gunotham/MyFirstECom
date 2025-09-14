package com.extron.MyFirstECom.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    
    private String street;
    private String city;
    private String state;
    private String pinCode;
    private String country;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private Users user;
}

