package com.quickorder.usuarios.service;

import com.quickorder.usuarios.dto.UsuariosRequestDTO;
import com.quickorder.usuarios.dto.UsuariosResponseDTO;
import com.quickorder.usuarios.model.UsuariosModel;
import com.quickorder.usuarios.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuariosService {

    private final UsuariosRepository repository;

    public UsuariosResponseDTO registrarUsuario(UsuariosRequestDTO request) {
        log.info("Registrando usuario: {}", request.getUsername());

        UsuariosModel usuario = new UsuariosModel();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(request.getPassword()); // Simplicidad: texto plano por ahora
        usuario.setEmail(request.getEmail());
        usuario.setRol(request.getRol());

        UsuariosModel guardado = repository.save(usuario);

        UsuariosResponseDTO response = new UsuariosResponseDTO();
        response.setId(guardado.getId());
        response.setUsername(guardado.getUsername());
        response.setEmail(guardado.getEmail());
        response.setRol(guardado.getRol());

        return response;
    }

    public UsuariosResponseDTO obtenerPorUsername(String username) {
        UsuariosModel u = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuariosResponseDTO response = new UsuariosResponseDTO();
        response.setId(u.getId());
        response.setUsername(u.getUsername());
        response.setEmail(u.getEmail());
        response.setRol(u.getRol());
        return response;
    }
}
