package com.quickorder.notificaciones.controller;

import com.quickorder.notificaciones.dto.NotificacionesRequestDTO;
import com.quickorder.notificaciones.dto.NotificacionesResponseDTO;
import com.quickorder.notificaciones.service.NotificacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificaciones")
@RequiredArgsConstructor
public class NotificacionesController {
    private final NotificacionesService service;

    @PostMapping
    public ResponseEntity<NotificacionesResponseDTO> enviar(@RequestBody NotificacionesRequestDTO request) {
        return ResponseEntity.ok(service.enviarNotificacion(request));
    }
}
