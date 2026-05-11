package com.quickorder.pagos.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PagoRequestDTO {
    private Long pedidoId;
    private BigDecimal montoTotal;
    private String metodoPago;
}
