package com.quickorder.notificaciones.service;

import com.quickorder.notificaciones.dto.NotificacionesRequestDTO;
import com.quickorder.notificaciones.dto.NotificacionesResponseDTO;
import com.quickorder.notificaciones.model.NotificacionesModel;
import com.quickorder.notificaciones.repsitory.NotificacionesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionesService {

    private final NotificacionesRepository repository;

    public NotificacionesResponseDTO enviarNotificacion(NotificacionesRequestDTO request) {
        log.info("ENVIANDO NOTIFICACIÓN {} A CLIENTE {}: {}",
                request.getTipo(), request.getClienteId(), request.getMensaje());

        NotificacionesModel model = new NotificacionesModel();
        model.setClienteId(request.getClienteId());
        model.setMensaje(request.getMensaje());
        model.setTipo(request.getTipo());

        NotificacionesModel guardado = repository.save(model);

        NotificacionesResponseDTO response = new NotificacionesResponseDTO();
        response.setId(guardado.getId());
        response.setClienteId(guardado.getClienteId());
        response.setTipo(guardado.getTipo());
        response.setMensaje(guardado.getMensaje());
        response.setFechaEnvio(guardado.getFechaEnvio());

        return response;
    }
}
