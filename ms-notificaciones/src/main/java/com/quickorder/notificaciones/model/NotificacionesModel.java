package com.quickorder.notificaciones.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
public class NotificacionesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private String mensaje;
    private String tipo;
    private LocalDateTime fechaEnvio;

    @PrePersist
    protected void onCreate() {
        this.fechaEnvio = LocalDateTime.now();
    }
}
