package com.quickorder.despachos.dto;

import lombok.Data;

@Data
public class DespachoRequestDTO {
    private Long pedidoId;
    private String direccionDestino;
}
