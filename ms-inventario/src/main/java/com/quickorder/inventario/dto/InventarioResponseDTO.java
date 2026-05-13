package com.quickorder.inventario.dto;

import lombok.Data;

@Data
public class InventarioResponseDTO {
    private Long productoId;
    private Integer cantidadDisponible;
    private Boolean tieneStock;
}
