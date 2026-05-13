package com.quickorder.pedidos.controller;

import com.quickorder.pedidos.dto.PedidoRequestDTO;
import com.quickorder.pedidos.dto.PedidoResponseDTO;
import com.quickorder.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody PedidoRequestDTO request) {
        PedidoResponseDTO response = pedidoService.orquestarPedido(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}