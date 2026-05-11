package com.quickorder.despachos.service;

import com.quickorder.despachos.dto.DespachoRequestDTO;
import com.quickorder.despachos.model.DespachoModel;
import com.quickorder.despachos.repository.DespachoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DespachoService {

    private final DespachoRepository despachoRepository;

    public void programarDespacho(DespachoRequestDTO request) {
        log.info("Programando despacho para el pedido ID: {} hacia la dirección: {}",
                request.getPedidoId(), request.getDireccionDestino());

        DespachoModel despacho = new DespachoModel();
        despacho.setPedidoId(request.getPedidoId());
        despacho.setDireccionDestino(request.getDireccionDestino());
        despacho.setEstado("PROGRAMADO");
        despacho.setFechaProgramacion(LocalDateTime.now());

        despachoRepository.save(despacho);
        log.info("Despacho programado con éxito.");
    }
}
