package com.extron.MyFirstECom.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private BigDecimal price;
}
