package com.quickorder.detalle_pedido.service;

import com.quickorder.detalle_pedido.dto.DetalleItemRequestDTO;
import com.quickorder.detalle_pedido.dto.DetalleResponseDTO;
import com.quickorder.detalle_pedido.model.DetallePedidoModel;
import com.quickorder.detalle_pedido.repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetallePedidoService {

    private final DetallePedidoRepository repository;

    public DetalleResponseDTO guardarDetalles(Long pedidoId, List<DetalleItemRequestDTO> items) {
        log.info("Guardando {} detalles para el pedido ID: {}", items.size(), pedidoId);

        List<DetallePedidoModel> detalles = items.stream().map(dto -> {
            DetallePedidoModel modelo = new DetallePedidoModel();
            modelo.setPedidoId(pedidoId);
            modelo.setProductoId(dto.getProductoId());
            modelo.setCantidad(dto.getCantidad());
            modelo.setPrecioUnitario(dto.getPrecioUnitario());
            return modelo;
        }).collect(Collectors.toList());

        repository.saveAll(detalles);

        DetalleResponseDTO response = new DetalleResponseDTO();
        response.setPedidoId(pedidoId);
        response.setCantidadItems(detalles.size());
        response.setMensaje("Detalles registrados correctamente");

        return response;
    }
}
