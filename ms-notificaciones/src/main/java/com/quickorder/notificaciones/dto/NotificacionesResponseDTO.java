package com.quickorder.notificaciones.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificacionesResponseDTO {
    private Long id;
    private Long clienteId;
    private String tipo;
    private String mensaje;
    private LocalDateTime fechaEnvio;
}