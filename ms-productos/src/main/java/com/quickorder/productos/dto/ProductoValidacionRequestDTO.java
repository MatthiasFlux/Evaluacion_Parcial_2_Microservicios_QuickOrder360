package com.quickorder.productos.dto;

import lombok.Data;

@Data
public class ProductoValidacionRequestDTO {
    private Long productoId;
    private Long cantidad;
}
