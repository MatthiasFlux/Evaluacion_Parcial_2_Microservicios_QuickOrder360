package com.quickorder.usuarios.dto;

import lombok.Data;

@Data
public class UsuariosResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String rol;
}
