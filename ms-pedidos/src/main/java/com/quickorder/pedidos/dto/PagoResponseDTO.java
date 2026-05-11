package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class PagoResponseDTO {
    private Long id;
    private String estado;
    private String transaccionId;
}
