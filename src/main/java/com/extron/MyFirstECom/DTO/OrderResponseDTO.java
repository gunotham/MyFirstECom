package com.extron.MyFirstECom.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private Date orderedAt;
    private BigDecimal amount;
    List<OrderItemResponseDTO> items;
    
}
