package com.quickorder.pagos.service;

import com.quickorder.pagos.dto.PagoRequestDTO;
import com.quickorder.pagos.dto.PagoResponseDTO;
import com.quickorder.pagos.model.PagoModel;
import com.quickorder.pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoResponseDTO procesarPago(PagoRequestDTO request) {
        log.info("Procesando pago simple para el pedido: {}", request.getPedidoId());

        PagoModel pago = new PagoModel();
        pago.setPedidoId(request.getPedidoId());
        pago.setMonto(request.getMonto());
        pago.setMetodoPago(request.getMetodoPago());
        pago.setEstado("APROBADO");

        PagoModel pagoGuardado = pagoRepository.save(pago);

        PagoResponseDTO response = new PagoResponseDTO();
        response.setIdPago(pagoGuardado.getId());
        response.setPedidoId(pagoGuardado.getPedidoId());
        response.setEstado(pagoGuardado.getEstado());
        response.setFechaPago(pagoGuardado.getFechaPago());

        log.info("Pago {} aprobado exitosamente", pagoGuardado.getId());
        return response;
    }
}
