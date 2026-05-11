package com.quickorder.pedidos.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
}
