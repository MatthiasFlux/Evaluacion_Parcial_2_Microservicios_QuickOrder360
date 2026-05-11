package com.quickorder.pagos.service;

import com.quickorder.pagos.dto.PagoRequestDTO;
import com.quickorder.pagos.dto.PagoResponseDTO;
import com.quickorder.pagos.model.PagoModel;
import com.quickorder.pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoResponseDTO procesarPago(PagoRequestDTO request) {
        log.info("Procesando pago con tarjeta para el pedido ID: {} por el monto de ${}",
                request.getPedidoId(), request.getMontoTotal());

        PagoModel pago = new PagoModel();
        pago.setPedidoId(request.getPedidoId());
        pago.setMontoTotal(request.getMontoTotal());
        pago.setMetodoPago(request.getMetodoPago());

        pago.setEstado("APROBADO");
        pago.setTransaccionId(UUID.randomUUID().toString());
        pago.setFechaPago(LocalDateTime.now());

        PagoModel pagoGuardado = pagoRepository.save(pago);
        log.info("Pago aprobado con éxito. Transacción ID: {}", pagoGuardado.getTransaccionId());

        PagoResponseDTO response = new PagoResponseDTO();
        response.setId(pagoGuardado.getId());
        response.setEstado(pagoGuardado.getEstado());
        response.setTransaccionId(pagoGuardado.getTransaccionId());

        return response;
    }
}