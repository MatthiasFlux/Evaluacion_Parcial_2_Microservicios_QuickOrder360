package com.quickorder.despachos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "despachos")
@Data
public class DespachoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId; // Referencia al ms-pedidos

    @Column(nullable = false)
    private String direccionEntrega;

    @Column(nullable = false)
    private String estado; // EJ: PREPARANDO, EN_RUTA, ENTREGADO

    private String trackingNumber; // Número de seguimiento

    private LocalDateTime fechaActualizacion;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
