package com.quickorder.pedidos.dto;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoResponseDTO {

    private Long idPedido;
    private String estado;
    private BigDecimal totalPagar;
    private LocalDateTime fecha;

}
