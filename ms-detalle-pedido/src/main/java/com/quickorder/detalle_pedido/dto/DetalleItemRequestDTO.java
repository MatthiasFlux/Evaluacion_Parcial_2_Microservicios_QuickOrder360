package com.quickorder.detalle_pedido.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetalleItemRequestDTO {
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
