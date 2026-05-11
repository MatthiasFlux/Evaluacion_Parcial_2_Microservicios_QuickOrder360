package com.quickorder.pagos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false)
    private BigDecimal montoTotal;

    @Column(nullable = false, length = 50)
    private String metodoPago;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, unique = true)
    private String transaccionId;

    @Column(nullable = false)
    private LocalDateTime fechaPago;
}
