package com.quickorder.pedidos.controller;

import com.quickorder.pedidos.dto.PedidoRequestDTO;
import com.quickorder.pedidos.model.PedidoModel;
import com.quickorder.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Slf4j
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoModel> crearPedido(@Valid @RequestBody PedidoRequestDTO request) {
        log.info("Recibiendo petición REST para crear un nuevo pedido");

        PedidoModel nuevoPedido = pedidoService.nuevoPedido(request);

        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }
}