package com.quickorder.detalle_pedido.dto;

import lombok.Data;
import java.util.List;

@Data
public class DetalleResponseDTO {
    private Long pedidoId;
    private Integer cantidadItems;
    private String mensaje;
}
