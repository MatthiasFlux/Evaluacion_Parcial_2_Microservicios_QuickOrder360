package com.quickorder.pedidos.service;

import com.quickorder.pedidos.dto.*;
import com.quickorder.pedidos.model.PedidoModel;
import com.quickorder.pedidos.repository.PedidoRepository;
import com.quickorder.pedidos.client.ClientModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClientModel externalClient;

    public PedidoModel nuevoPedido(PedidoRequestDTO request) {
        log.info("Iniciando procesamiento de pedido para cliente: {}", request.getClienteId());

        ClienteDTO cliente = externalClient.validarCliente(request.getClienteId());

        List<ProductoDetalleDTO> productosInfo = externalClient.obtenerInfoProductos(request.getProductos());

        PedidoModel pedido = prepararPedido(request, productosInfo, cliente.getId());

        PedidoModel pedidoGuardado = guardarPedidoYDescontarStock(pedido, request);

        PagoResponseDTO pago = externalClient.procesarPago(pedidoGuardado.getId(), pedidoGuardado.getTotal());

        if ("APROBADO".equals(pago.getEstado())) {
            externalClient.generarDespacho(pedidoGuardado.getId(), "Dirección de prueba");
            actualizarEstadoPedido(pedidoGuardado.getId(), "PAGADO_Y_DESPACHADO");
        }

        return pedidoGuardado;
    }

    @Transactional
    public PedidoModel guardarPedidoYDescontarStock(PedidoModel pedido, PedidoRequestDTO request) {
        log.info("Guardando pedido en base de datos local...");
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void actualizarEstadoPedido(Long id, String estado) {
        pedidoRepository.findById(id).ifPresent(p -> p.setEstado(estado));
    }

    private PedidoModel prepararPedido(PedidoRequestDTO request, List<ProductoDetalleDTO> info, Long clienteId) {
        PedidoModel pedido = new PedidoModel();
        pedido.setClienteId(clienteId);
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        BigDecimal totalCalculado = BigDecimal.ZERO;

        for (PedidoProductoRequestDTO productoPeticion : request.getProductos()) {
            BigDecimal precioUnitario = info.stream()
                    .filter(p -> p.getId().equals(productoPeticion.getProductoId()))
                    .findFirst()
                    .map(ProductoDetalleDTO::getPrecio)
                    .orElse(BigDecimal.ZERO);

            BigDecimal cantidad = BigDecimal.valueOf(productoPeticion.getCantidad());
            BigDecimal subtotal = precioUnitario.multiply(cantidad);

            totalCalculado = totalCalculado.add(subtotal);
        }

        pedido.setTotal(totalCalculado);

        return pedido;
    }
}