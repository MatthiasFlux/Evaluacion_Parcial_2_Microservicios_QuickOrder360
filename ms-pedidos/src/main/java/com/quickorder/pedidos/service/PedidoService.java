package com.quickorder.pedidos.service;

import com.quickorder.pedidos.client.*;
import com.quickorder.pedidos.dto.*;
import com.quickorder.pedidos.model.PedidoModel;
import com.quickorder.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;


    private final InventarioClient inventarioClient;
    private final DetallePedidoClient detalleClient;
    private final PagoClient pagoClient;
    private final DespachoClient despachoClient;
    private final NotificacionClient notificacionClient;

    public PedidoResponseDTO orquestarPedido(PedidoRequestDTO request) {
        log.info("Iniciando orquestación para cliente ID: {}", request.getClienteId());

        // 1. Validar Stock (ahora pasando cantidad y recibiendo DTO)
        for (ItemPedidoDTO item : request.getItems()) {
            InventarioResponseDTO stock = inventarioClient.verificarStock(item.getProductoId(), item.getCantidad());
            if (!stock.getTieneStock()) {
                throw new RuntimeException("Sin stock para el producto ID: " + item.getProductoId());
            }
        }

        // 2. Guardar el encabezado del Pedido
        PedidoModel pedido = new PedidoModel();
        pedido.setClienteId(request.getClienteId());
        pedido.setEstado("PROCESANDO");
        pedido.setFechaCreacion(LocalDateTime.now());

        // Calculamos el total de forma simple sumando los items
        BigDecimal total = request.getItems().stream()
                .map(i -> i.getPrecio().multiply(new BigDecimal(i.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setTotal(total);

        PedidoModel pedidoGuardado = pedidoRepository.save(pedido);

        // 3. Guardar detalles en ms-detalle-pedido
        detalleClient.crearDetalles(pedidoGuardado.getId(), request.getItems());

        // 4. Procesar Pago (ms-pagos - Siempre aprobado por tu regla)
        PagoRequestDTO pagoReq = new PagoRequestDTO();
        pagoReq.setPedidoId(pedidoGuardado.getId());
        pagoReq.setMonto(total);
        pagoReq.setMetodoPago("TARJETA");
        pagoClient.realizarPago(pagoReq);

        // 5. Programar Despacho (ms-despachos)
        DespachoRequestDTO despachoReq = new DespachoRequestDTO();
        despachoReq.setPedidoId(pedidoGuardado.getId());
        despachoReq.setDireccionEntrega("Dirección registrada del cliente"); // Simplificado
        despachoClient.crearDespacho(despachoReq);

        // 6. Notificar al cliente (ms-notificaciones)
        NotificacionRequestDTO notifReq = new NotificacionRequestDTO();
        notifReq.setClienteId(request.getClienteId());
        notifReq.setMensaje("Tu pedido #" + pedidoGuardado.getId() + " ha sido aprobado y va en camino.");
        notifReq.setTipo("EMAIL");
        notificacionClient.enviar(notifReq);

        // 7. Actualizar estado final del pedido localmente
        pedidoGuardado.setEstado("COMPLETADO");
        pedidoRepository.save(pedidoGuardado);

        // 8. Armar respuesta
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setIdPedido(pedidoGuardado.getId());
        response.setEstado(pedidoGuardado.getEstado());
        response.setTotalPagar(pedidoGuardado.getTotal());
        response.setFecha(pedidoGuardado.getFechaCreacion());

        log.info("¡Flujo completo! Pedido {} finalizado con éxito", pedidoGuardado.getId());
        return response;
    }
}