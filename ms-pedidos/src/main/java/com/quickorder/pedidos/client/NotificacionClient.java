package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.NotificacionRequestDTO;
import com.quickorder.pedidos.dto.NotificacionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-notificaciones")
public interface NotificacionClient {

    @PostMapping("/api/v1/notificaciones")
    NotificacionResponseDTO enviar(@RequestBody NotificacionRequestDTO request);

}
