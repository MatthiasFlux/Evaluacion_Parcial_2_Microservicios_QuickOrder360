package com.quickorder.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventario")
@Data
public class InventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long productoId; // ID del producto en ms-productos

    @Column(nullable = false)
    private Integer cantidadDisponible;
}
