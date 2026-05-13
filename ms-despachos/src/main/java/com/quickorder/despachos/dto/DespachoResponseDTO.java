package com.quickorder.despachos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DespachoResponseDTO {
    private Long idDespacho;
    private Long pedidoId;
    private String trackingNumber;
    private String estado;
    private LocalDateTime fechaActualizacion;
}
