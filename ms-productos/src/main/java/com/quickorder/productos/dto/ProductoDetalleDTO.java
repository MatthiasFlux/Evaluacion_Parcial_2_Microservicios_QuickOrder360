package com.quickorder.productos.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoDetalleDTO {
    private Long id;
    private String sku;
    private BigDecimal precio;
    private Integer stockDisponible;
}
