package com.quickorder.notificaciones.dto;

import lombok.Data;

@Data
public class NotificacionesRequestDTO {
    private Long clienteId;
    private String mensaje;
    private String tipo;
}
