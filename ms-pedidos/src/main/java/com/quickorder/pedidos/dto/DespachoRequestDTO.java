package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class DespachoRequestDTO {
    private Long pedidoId;
    private String direccionEntrega;
}
