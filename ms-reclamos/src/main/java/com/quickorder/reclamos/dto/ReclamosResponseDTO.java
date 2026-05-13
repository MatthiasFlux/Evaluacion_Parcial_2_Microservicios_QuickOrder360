package com.quickorder.reclamos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReclamosResponseDTO {
    private Long id;
    private Long pedidoId;
    private String estado;
    private String descripcion;
    private LocalDateTime fechaCreacion;
}
