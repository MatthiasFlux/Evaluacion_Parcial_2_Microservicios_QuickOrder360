package com.quickorder.pagos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PagoResponseDTO {
    private Long idPago;
    private Long pedidoId;
    private String estado;
    private LocalDateTime fechaPago;
}
