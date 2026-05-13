package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class NotificacionRequestDTO {
    private Long clienteId;
    private String mensaje;
    private String tipo;
}
