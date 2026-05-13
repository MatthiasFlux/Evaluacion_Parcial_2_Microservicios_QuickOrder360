package com.quickorder.reclamos.dto;

import lombok.Data;

@Data
public class ReclamosRequestDTO {
    private Long pedidoId;
    private Long clienteId;
    private String descripcion;
}
