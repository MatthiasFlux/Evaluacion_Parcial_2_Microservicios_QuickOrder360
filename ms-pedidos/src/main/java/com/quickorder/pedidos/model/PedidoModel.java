package com.quickorder.pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clienteId", nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false)
    private BigDecimal total;
}
