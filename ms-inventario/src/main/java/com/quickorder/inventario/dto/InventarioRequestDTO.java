package com.quickorder.inventario.dto;

import lombok.Data;

@Data
public class InventarioRequestDTO {
    private Long productoId;
    private Integer cantidad;
}
