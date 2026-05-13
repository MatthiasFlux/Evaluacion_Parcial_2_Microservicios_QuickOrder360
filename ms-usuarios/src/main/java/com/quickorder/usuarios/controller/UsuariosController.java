package com.quickorder.usuarios.controller;

import com.quickorder.usuarios.dto.UsuariosRequestDTO;
import com.quickorder.usuarios.dto.UsuariosResponseDTO;
import com.quickorder.usuarios.service.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuariosService service;

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuariosResponseDTO registrar(@RequestBody UsuariosRequestDTO request) {
        return service.registrarUsuario(request);
    }

    @GetMapping("/{username}")
    public UsuariosResponseDTO buscar(@PathVariable String username) {
        return service.obtenerPorUsername(username);
    }
}
