package com.quickorder.clientes.controller;

import com.quickorder.clientes.model.ClienteModel;
import com.quickorder.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteModel> crearCliente(@RequestBody ClienteModel cliente) {
        return new ResponseEntity<>(clienteService.guardarCliente(cliente), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));

    }
}
