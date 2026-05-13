package com.quickorder.pedidos.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemPedidoDTO {
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precio;
}
