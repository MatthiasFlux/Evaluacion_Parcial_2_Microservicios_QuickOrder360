package com.quickorder.despachos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "despacho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespachoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false, length = 255)
    private String direccionDestino;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fechaProgramacion;
}
