package com.quickorder.usuarios.dto;

import lombok.Data;

@Data
public class UsuariosRequestDTO {
    private String username;
    private String password;
    private String email;
    private String rol;
}
