package com.quickorder.pagos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
public class PagoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private String estado;

    private LocalDateTime fechaPago;

    @PrePersist
    protected void onCreate() {
        this.fechaPago = LocalDateTime.now();
    }
}