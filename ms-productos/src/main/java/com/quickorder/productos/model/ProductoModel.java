package com.quickorder.productos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String sku;
    private BigDecimal precio;
    private Integer stock;
}