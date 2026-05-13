package com.quickorder.detalle_pedido.controller;

import com.quickorder.detalle_pedido.dto.DetalleItemRequestDTO;
import com.quickorder.detalle_pedido.dto.DetalleResponseDTO;
import com.quickorder.detalle_pedido.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService service;

    @PostMapping("/{pedidoId}")
    public DetalleResponseDTO crearDetalles(
            @PathVariable Long pedidoId,
            @RequestBody List<DetalleItemRequestDTO> items) {
        return service.guardarDetalles(pedidoId, items);
    }
}
