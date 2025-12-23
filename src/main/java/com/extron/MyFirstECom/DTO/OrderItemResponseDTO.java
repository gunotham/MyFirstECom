package com.extron.MyFirstECom.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseDTO {
    private ProductDTO product;
    private BigDecimal orderedPrice;
    private Integer quantity;
}
