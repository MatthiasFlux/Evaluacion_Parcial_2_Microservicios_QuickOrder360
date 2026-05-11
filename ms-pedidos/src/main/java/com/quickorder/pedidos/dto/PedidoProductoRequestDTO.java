package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class PedidoProductoRequestDTO {

    private Long productoId;
    private Long cantidad;

}
