package com.extron.MyFirstECom.DTO;

import lombok.Data;

@Data
public class AddressDTO {
    
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String pinCode;
    private String country;
}
