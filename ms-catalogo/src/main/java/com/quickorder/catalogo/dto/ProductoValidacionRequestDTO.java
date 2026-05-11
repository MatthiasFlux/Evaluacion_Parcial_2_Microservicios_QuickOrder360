package com.quickorder.catalogo.dto;

import lombok.Data;

@Data
public class ProductoValidacionRequestDTO {
    private Long productoId;
    private Long cantidad;
}
