package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class PagoResponseDTO {
    private Long idPago;
    private String estado;
    private String transaccionId;
}
