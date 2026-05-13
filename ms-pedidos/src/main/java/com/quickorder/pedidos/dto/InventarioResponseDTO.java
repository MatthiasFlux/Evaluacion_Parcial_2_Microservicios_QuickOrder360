package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class InventarioResponseDTO {
    private Long productoId;
    private Integer cantidadDisponible;
    private Boolean tieneStock;
}
