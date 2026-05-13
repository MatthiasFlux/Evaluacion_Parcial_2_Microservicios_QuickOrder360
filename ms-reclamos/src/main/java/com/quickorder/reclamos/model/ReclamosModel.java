package com.quickorder.reclamos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reclamos")
@Data
public class ReclamosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private String estado; // PENDIENTE, EN_REVISION, RESUELTO

    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
