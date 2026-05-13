package com.quickorder.pedidos.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PagoRequestDTO {
    private Long pedidoId;
    private BigDecimal monto;
    private String metodoPago;
}
