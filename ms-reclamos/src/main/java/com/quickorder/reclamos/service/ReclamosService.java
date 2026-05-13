package com.quickorder.reclamos.service;

import com.quickorder.reclamos.dto.ReclamosRequestDTO;
import com.quickorder.reclamos.dto.ReclamosResponseDTO;
import com.quickorder.reclamos.model.ReclamosModel;
import com.quickorder.reclamos.repository.ReclamosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReclamosService {

    private final ReclamosRepository repository;

    public ReclamosResponseDTO crearReclamo(ReclamosRequestDTO request) {
        log.info("Registrando nuevo reclamo para el pedido: {}", request.getPedidoId());

        ReclamosModel reclamo = new ReclamosModel();
        reclamo.setPedidoId(request.getPedidoId());
        reclamo.setClienteId(request.getClienteId());
        reclamo.setDescripcion(request.getDescripcion());
        reclamo.setEstado("PENDIENTE");

        ReclamosModel guardado = repository.save(reclamo);

        ReclamosResponseDTO response = new ReclamosResponseDTO();
        response.setId(guardado.getId());
        response.setPedidoId(guardado.getPedidoId());
        response.setEstado(guardado.getEstado());
        response.setDescripcion(guardado.getDescripcion());
        response.setFechaCreacion(guardado.getFechaCreacion());

        return response;
    }
}
