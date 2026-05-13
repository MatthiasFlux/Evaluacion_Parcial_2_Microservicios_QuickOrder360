package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class DespachoResponseDTO {
    private Long idDespacho;
    private String trackingNumber;
    private String estado;
}
