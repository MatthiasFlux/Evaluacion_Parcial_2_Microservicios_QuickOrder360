package com.quickorder.despachos.service;

import com.quickorder.despachos.dto.DespachoRequestDTO;
import com.quickorder.despachos.dto.DespachoResponseDTO;
import com.quickorder.despachos.model.DespachoModel;
import com.quickorder.despachos.repository.DespachoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DespachoService {

    private final DespachoRepository despachoRepository;

    public DespachoResponseDTO programarDespacho(DespachoRequestDTO request) {
        log.info("Programando despacho para el pedido: {}", request.getPedidoId());

        DespachoModel despacho = new DespachoModel();
        despacho.setPedidoId(request.getPedidoId());
        despacho.setDireccionEntrega(request.getDireccionEntrega());
        despacho.setEstado("PREPARANDO");

        despacho.setTrackingNumber("TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        DespachoModel despachoGuardado = despachoRepository.save(despacho);

        DespachoResponseDTO response = new DespachoResponseDTO();
        response.setIdDespacho(despachoGuardado.getId());
        response.setPedidoId(despachoGuardado.getPedidoId());
        response.setTrackingNumber(despachoGuardado.getTrackingNumber());
        response.setEstado(despachoGuardado.getEstado());
        response.setFechaActualizacion(despachoGuardado.getFechaActualizacion());

        log.info("Despacho {} programado con Tracking: {}", despachoGuardado.getId(), despachoGuardado.getTrackingNumber());
        return response;
    }
}
